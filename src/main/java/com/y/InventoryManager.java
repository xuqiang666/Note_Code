package com.y;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自增操作保证原子和避免溢出
 * CAS
 */
public class InventoryManager{

    private final AtomicInteger itemsInInventory = new AtomicInteger(100);

    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        //manager.testnextItem();
        manager.nextItem();
        System.out.println(manager.itemsInInventory.get());
    }

    public final void nextItem() {
        while (true) {
            int old = itemsInInventory.get();
            if (old == Integer.MAX_VALUE) {
                throw new ArithmeticException("Integer overflow");
            }
            int next = old + 1; // Increment
            if (itemsInInventory.compareAndSet(old, next)) {
                break;
            }
        } // End while
    } // End nextItem()


    /**
     * for中的if只会进入一次，第一次进入时，old为100，itemInventory为100，所以给itemInventory赋值next，返回true
     * 第二次进入时，old为100而itemInventory为101
     *
     *  public final native boolean compareAndSwapInt(Object o, long offset,
     *                                                 int expected,
     *                                                 int x);
     * 读取传入对象o在内存中偏移量为offset位置的值与期望值expected作比较。相等就把x值赋值给offset位置的值。方法返回true
     * compareAndSet方法调用 unsafe.compareAndSwapInt(this, valueOffset, expect, update);
     * 此处this为itemsInInventory（AtomicInteger），valueOffeset为itemsInInventory.value，expect为old，update为next
     */
    public final void testnextItem() {
        int old = itemsInInventory.get();
        while (true) {
            if (old == Integer.MAX_VALUE) {
                throw new ArithmeticException("Integer overflow");
            }
            int next = old + 1;
            if (itemsInInventory.compareAndSet(old, next)) {
                //break;
                System.out.println("只进入一次");
            }
        }
    }
}
