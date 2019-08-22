package com.company;

public class ThreadService {

    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable run) {

        executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(run);
                runner.setDaemon(true);
                runner.start();
                try {
                    /**
                     * 通过join方式，使得只有当runner执行完毕时，才允许execute继续执行，让finished = true。
                     * 如果这里不使用join，在多线程环境下，executThread先执行完毕，导致守护进程runner会随着executeThread一起关闭。
                     */
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    System.out.println("线程被打断！");
                }
            }
        };

        executeThread.start();

    }

    public void shutdown(long millis) {

        long currentTime = System.currentTimeMillis();

        /**
         *  如果未完成，就一直循环
         */
        while (!finished) {
            /**
             *  如果超过指定时间，就打断他
             */
            if ((System.currentTimeMillis() - currentTime) >= millis) {

                System.out.println("执行超时，需要被打断！");

                executeThread.interrupt();

                break;

            }

            try {

                executeThread.sleep(100);

            } catch (InterruptedException e) {

                System.out.println("跳出循环！");

                break;

            }

        }

    }

}
