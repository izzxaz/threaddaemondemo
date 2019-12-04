package com.company;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long begin = System.currentTimeMillis();
        /**
         * 什么也不干，就让他(runner)一直卡死在循环里。
         * 后面调用方法，结束掉executeThread,让runner守护线程随着执行线程结束。
         */
        service.execute(()->{
            int i=0;
            while (i<10){
                System.out.println("守护线程还活着");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        });

        service.shutdown(5000);

        long end = System.currentTimeMillis();
        System.out.println("main执行了"+(end-begin)+"ms");
    }
}
