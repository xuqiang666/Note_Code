package com.x.thread;

import java.util.ArrayList;
import java.util.List;


/**
 * create by 许庆之 on 2020/2/11.
 * Thread线程调用相关
 */
public class NameList {
    private List<String> names = new ArrayList();

    private synchronized void add(String str){
        names.add(str);
    }

    private synchronized void printall() {
        for(int i=0;i<names.size();i++)
        {
            System.out.println(names.get(i)+" ");
        }
    }

    public static void main(String[] args) {
        final NameList sl = new NameList();
        for(int i=0;i<2;i++)
        {
            new Thread()
            {
                @Override
                public void run()
                {
                    sl.add("A");
                    sl.add("B");
                    sl.add("C");
                    sl.printall();
                }
            }.start();
        }
    }
}
