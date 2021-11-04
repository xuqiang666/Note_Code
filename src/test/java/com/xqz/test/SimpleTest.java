package com.xqz.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SimpleTest {

    @Test
    public void computeIfAbsentTest() {
        HashMap map = new HashMap();
        String key = "lalal";
        String v = "vu";

        ((HashSet<String>) map.computeIfAbsent(key, k -> new HashSet<String>())).add(v);

        HashMap<String, ArrayList<String>> map2 = new HashMap<>();
        String key2 = "lalal";
        String v2 = "vu";

        ((List)map2.computeIfAbsent(key2, (k) -> {
            return new ArrayList<>();
        })).add(v);
    }

    @Test
    public void test39943(){
        Object a = BigDecimal.valueOf(Long.parseLong("1"));
        Object b = null;
        // null进行强转不报错
        BigDecimal a1 = (BigDecimal) b;
        if(a1 == null) {
            System.out.println("0");
        }else {
            System.out.println("1");
        }
    }


    public void testdasddas(){
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        int size = map.size();
        long l = map.mappingCount();
    }

    @sun.misc.Contended static final class CounterCell {
        volatile long value;
        CounterCell(long x) { value = x; }
    }

    private transient volatile CounterCell[] counterCells;
    private transient volatile long baseCount;

    final long sumCount() {
        CounterCell[] as = counterCells;
        CounterCell a;
        long sum = baseCount;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if ((a = as[i]) != null)
                    sum += a.value;
            }
        }
        return sum;
    }

    final long sumCountx() {
        CounterCell[] as = counterCells;
        long sum = baseCount;
        if (as != null) {
            for (int i = 0; i < as.length; ++i) {
                if (as[i] != null)
                    sum += as[i].value;
            }
        }
        return sum;
    }

    @Test
    public void testsfsdfsdf(){
        StringJoiner joiner3 = new StringJoiner("','", "'", "'");
        joiner3.add("1").add("2");
        System.out.println(joiner3);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        String commaSeparatedNumbers = numbers.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining(", "));
        System.out.println(commaSeparatedNumbers);
    }

    public synchronized void syncMethod(){

    }


    public void syncBlock(){
        synchronized (SimpleTest.class){

        }
    }
}
