package com.x.thread.semaphore;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * semaphore 信号量，多线程运行控制器，控制统一时间并发的线程数量，最适用的场景就是限流
 */
public class SemaphoreExample {
    private static final int THREAD_SIZE = 30;
    /**
     * 同一时间，只有permits 数量的线程执行
     */
    private static final Semaphore S = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
        for (int i = 0; i < THREAD_SIZE * 2; i++) {
            int finalI = i;
            executorService.execute(() -> {
                try {
                    System.out.println("Semaphore out before:" + finalI + "  currentTimeMillis:" + System.currentTimeMillis());
                    S.acquire();
                    System.out.println(Thread.currentThread().getName() + "  currentTimeMillis: " + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("thread count: " + finalI);
                    S.release();
                    System.out.println("Semaphore out after: " + finalI + "   currentTimeMillis: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // sonar 让加上
                    Thread.currentThread().interrupt();
                }
            });
        }
        executorService.shutdown();
    }

    @Test
    public void test02() {
        Semaphore semapore = new Semaphore(5);
        boolean acquire = false;
        try {
            acquire = semapore.tryAcquire(3000, TimeUnit.MILLISECONDS);
            if (acquire) {
                // dosomething
            } else {
                System.out.println("未拿到访问权限");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (acquire) {
                semapore.release();
            }
        }
    }


    /**
     * Sempahore 信号量最经典的使用场景是限流，用于控制并发度。
     * 回想我们在开发系统时免不了要对接第三方系统，例如在快递行业的用户端系统（寄件）时，用户通过微信小程序进行下单时，
     * 需要手动填写收件人、寄件人信息等信息，十分繁琐与低效，能否从产品角度加以改善？
     * 当然可以，产品提成上传一张包含收件地址等信息等图片，通过 AI 等技术识别图片，自动提取图片中的有效信息并自动填充，提高用户体验。
     * 通过技术选型，我们敲定了百度的免费(付费)图片识别接口，但第三方提供的配额是有限的，
     * 特别是会限制接口的并发度，超过并发度的接口将会返回错误，特别是免费类的接口更加如此？
     * 该如何处理呢？Sempahore 闪亮登场。
     */
    @Test
    public void testImg() throws InterruptedException {
        List<String> images = getImages();
        // 只允许同时解析三个
        Semaphore semapore = new Semaphore(3);
        CountDownLatch countDownLatch = new CountDownLatch(images.size());
        for (String image : images) {
            boolean acquire = false;
            try {
                acquire = semapore.tryAcquire(3000, TimeUnit.MILLISECONDS);
                if (acquire) {
                    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> dosomething(image));
                    future.whenComplete((result, t) -> {
                        System.out.println("whenCompleta");
                        if (Objects.nonNull(t)) {
                            t.printStackTrace();
                        }
                    });
                } else {
                    System.out.println("未拿到访问权限");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                if (acquire) {
                    semapore.release();
                }
                countDownLatch.countDown();
            }
        }
        boolean await = countDownLatch.await(5000, TimeUnit.MILLISECONDS);
        if (await) {
            System.out.println("success!");
        } else {
            // 当超时时，许可仍然没被释放，后续操作仍然拿不到许可
            System.out.println("time out!");
        }
    }


    private List<String> getImages() {
        String[] strings = {"aaa", "bbb", "cccc"};
        return Arrays.asList(strings);
    }

    private String dosomething(String image) {
        return image + " complete!";
    }

    /**
     * 包装类，包装 Sempahore，并结合 AtomicBoolean，保证每一个 SempahoreReleaseOnlyOne 对象只会释放 Sempahore 一次。
     */
    class SemaphoreReleaseOnlyOne {
        private Semaphore semaphore;
        private AtomicBoolean release = new AtomicBoolean(false);

        public SemaphoreReleaseOnlyOne(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public void release() {
            if (release.compareAndSet(false, true)) {
                semaphore.release();
            }
        }
    }
}
