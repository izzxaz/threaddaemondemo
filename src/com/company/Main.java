package com.company;

public class Main {

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long begin = System.currentTimeMillis();
        /**
         * 什么也不干，就让他(runner)一直卡死在循环里。
         * 后面调用方法，结束掉executeThread,让runner守护线程随着执行线程结束。
         */
        service.execute(()->{
            while(true){

            }
        });

        service.shutdown(5000);

        long end = System.currentTimeMillis();
        System.out.println("执行了"+(end-begin)+"ms");
    }
}
