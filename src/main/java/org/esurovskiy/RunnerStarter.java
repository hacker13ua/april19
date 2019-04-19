package org.esurovskiy;


import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class RunnerStarter {
    public static void main(String[] args) throws InterruptedException {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountDownLatch latch = new CountDownLatch(5);
        new Runner(latch, "Runner 1").start();
        new Runner(latch, "Runner 2").start();
        new Runner(latch, "Runner 3").start();
        new Runner(latch, "Runner 4").start();
        new Runner(latch, "Runner 5").start();

        while (latch.getCount() > 0) {
            System.out.println(latch.getCount() + "... "+ new Date());
            Thread.sleep(1000);
            if (latch.getCount() == 1) {
                System.out.println("FIRE!");
            }
            latch.countDown();
        }

    }
}
