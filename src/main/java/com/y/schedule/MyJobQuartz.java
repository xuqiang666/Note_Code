package com.y.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * （1）Scheduler：任务调度器，是执行任务调度的控制器。本质上是一个计划调度容器，注册了全部Trigger和对应的JobDetail， 使用线程池作为任务运行的基础组件，提高任务执行效率。
 * <p>
 * （2）Trigger：触发器，用于定义任务调度的时间规则，告诉任务调度器什么时候触发任务，其中CronTrigger是基于cron表达式构建的功能强大的触发器。
 * <p>
 * （3）Calendar：日历特定时间点的集合。一个trigger可以包含多个Calendar，可用于排除或包含某些时间点。
 * <p>
 * （4）JobDetail：是一个可执行的工作，用来描述Job实现类及其它相关的静态信息，如Job的名称、监听器等相关信息。
 * <p>
 * （5）Job：任务执行接口，只有一个execute方法，用于执行真正的业务逻辑。
 * <p>
 * （6）JobStore：任务存储方式，主要有RAMJobStore和JDBCJobStore，RAMJobStore是存储在JVM的内存中，有丢失和数量受限的风险，JDBCJobStore是将任务信息持久化到数据库中，支持集群。
 */
public class MyJobQuartz implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("要去数据库扫描啦。。。");
    }

    public static void main(String[] args) throws Exception {
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJobQuartz.class)
                .withIdentity("job1", "group1").build();
        // 创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger1", "group3")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(3).repeatForever())
                .build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        // 将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        // 调度器开始调度任务
        scheduler.start();
    }
}
