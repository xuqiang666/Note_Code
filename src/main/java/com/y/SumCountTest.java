package com.y;

/**
 * 测试 sumCount 中奇怪的优化
 */
public class SumCountTest {

    @sun.misc.Contended
    static final class CounterCell {
        volatile long value;

        CounterCell(long x) {
            value = x;
        }
    }

    private transient volatile CounterCell[] counterCells;
    private transient volatile long baseCount;

    final long sumCount() {
        CounterCell[] as = counterCells;
        CounterCell a;
        long sum = baseCount;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null) {
                    sum += a.value;
                }
            }
        }
        return sum;
    }

    final long sumCountx() {
        CounterCell[] as = counterCells;
        long sum = baseCount;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if (as[i] != null) {
                    sum += as[i].value;
                }
            }
        }
        return sum;
    }
}
