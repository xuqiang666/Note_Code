package com.kk;

import java.util.*;

/**
 * 给出一个升序数组，从中取出该数组子集相加绝对值最接近目标数的集合
 * @author  yaoguang_wu
 * @date  2021-11-06
 */
public class ClosestAlgorithmTool {
    private static int count = 0;
    public static void main(String[] args) {
//        int[] arr = {2,30,42,67};
//        int[] arr = {3,6,7,15,24};
        int[] arr = {3,6,7,15,24,27,34,38,40,41,50,52,52,66,72,74,78};
        long start = System.currentTimeMillis();
        List<int[]> targetCollection = getTargetCollection(arr, 55);
        for (int[] ints : targetCollection) {
            System.out.print("[");
            System.out.print(ints[0]);
            for (int i = 1; i < ints.length; i++) {
                System.out.print(","+ints[i]);
            }
            System.out.println("]");
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时长： "+(end - start)/1000.0+" 秒");
        System.out.println("组合次数: "+ count +"次");
        System.out.println("符合要求的个数为： "+ targetCollection.size() +" 个");
    }
    /**
     * 从指定数组中取出相加绝对值最接近目标数的子集集合
     *
     * @param orgArr 原始数组
     * @param target 目标数
     */
    private static List<int[]> getTargetCollection(int[] orgArr, int target) {
        //存储结果的集合，key为Integer代表与目标值相差的绝对值，value为List集合代表相同差距的数组集合
        TreeMap<Integer, ArrayList<int[]>> resultMap = new TreeMap<>();
        //根据组合的元素个数n进行递归求组合结果
        for (int n = 1; n <= orgArr.length; n++) {
            //简化循环操作一
            int sum = 0;
            //本次组合个数
            int comCount = n;
            //求出集合中n个元素的最大值
            for (int j = orgArr.length - 1; comCount > 0; j--, comCount--) {
                sum += orgArr[j];
            }
            //当组合个数为n，而原集合中最大的n个数之和都小于目标数，那本次就没必要在组合了，直接跳过
            if (target > sum) {
                //这种特殊情况不能跳过，集合的所有数据之和还比目标函数小
                if (n != orgArr.length){
                    continue;
                }
            }
            //步入正题
            //用来存储组合本次结果数组
            int[] newArr = new int[n];
            int primTarget = 0;
            for (int j = 0; j < n; j++) {
                primTarget += orgArr[j];
            }
            //用一个长度为2的二维数组，int[0][0]元素存储上次组合后目标值与组合值的差距值，第二个元素存储本次差距值，缩减循环次数
            int[][] prim = new int[2][2];
            prim[0][0] = target - primTarget;
            prim[0][1] = target - primTarget;
            prim[1][0] = 0;
            prim[1][1] = 0;
            combinationFunction(orgArr, newArr, 0, n, target, resultMap, prim);
        }
        Iterator<Map.Entry<Integer, ArrayList<int[]>>> iterator = resultMap.entrySet().iterator();
        while (iterator.hasNext()) {
            //因为是TreeMap，有默认排序，返回第一个即可
            Integer key = iterator.next().getKey();
            return resultMap.get(key);
        }
        return new ArrayList<>();
    }

    /**
     * 从指定数组中选取n个不重复数据组合,把与目标值相差的绝对值存入指定map集合中
     *
     * @param orgArr    原始数组集合
     * @param newArr    新数组，用来存储本次挑选出的数组集合
     * @param index     本次组合起始数组下标位置
     * @param n         本次剩余需要取出的元素的个数
     * @param target    目标值
     * @param resultMap 存储结果的集合
     * @param prim      二维数组，prim[0][0]上次执行组合值与目标值的差距，prim[0][1]本次次执行组合值与目标值的差距
     *                  prim[1][0]上次执行起始index下标值，prim[1][1]本次执行起始index下标值
     */
    private static void combinationFunction(int[] orgArr, int[] newArr, int index, int n, int target, Map<Integer, ArrayList<int[]>> resultMap, int[][] prim) {
        //当要取出的个数为0时，说明本次已经组合完成
        if (n == 0) {
            //记录本次取值的和
            int sum = 0;
            //临时变量数组，用来复制当前组合数组，用于接下来的存入结果集中
            int[] temArr = new int[newArr.length];
            //遍历求和和复制一份当前组合数组
            for (int i = 0; i < newArr.length; i++) {
                temArr[i] = newArr[i];
                sum += newArr[i];
            }
            //一代新人变旧人，简化循环用
            prim[0][0] = prim[0][1];
            prim[0][1] = target - sum;
            //得到本次取值的和与目标数的绝对值
            int abs = Math.abs(target - sum);
            //查看结果map是否包含当前差值的key,1，如果包含取出当前key包含的数组，把本次组合的数组（临时变量数组）加入集合，重新存储覆盖；
            if (resultMap.containsKey(abs)) {
                ArrayList<int[]> oldList = resultMap.get(abs);
                oldList.add(temArr);
                resultMap.put(abs, oldList);
            } else {
                //如果不存在，新建一个list集合，把临时变量数组添加进去
                ArrayList<int[]> newList = new ArrayList<>();
                newList.add(temArr);
                resultMap.put(abs, newList);
            }
            //计数器,测试可打开
            count++;
            return;
        }
        for (int i = index; i <= orgArr.length - n; i++) {
            //简化循环操作二
            //如果上次跟本次的差距值都小于0，那接下来的差距值只会更大，把n设为0，跳出当前循环
            if (prim[0][0] < 0 && prim[0][1] < 0) {
                //这里还需要满足前两次起始位置相同的情况
                if (prim[1][0] == prim[1][1]) {
                    //如果是第一次进来，还需要正常判断处理
                    if (prim[0][0] == prim[0][1]) {
                        //将提取出来的数依次放到新数组中
                        newArr[newArr.length - n] = orgArr[i];
                        combinationFunction(orgArr, newArr, i + 1, n - 1, target, resultMap, prim);
                    }
                    //跳出当前循环
                    break;
                }
            }
            //将提取出来的数依次放到新数组中
            newArr[newArr.length - n] = orgArr[i];
            //更新当前起始下标位置
            prim[1][0] = prim[1][1];
            prim[1][1] = i;
            combinationFunction(orgArr, newArr, i + 1, n - 1, target, resultMap, prim);
        }
    }
}
