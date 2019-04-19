package org.esurovskiy;

import java.util.concurrent.ForkJoinPool;


public class RecursiveSummatorMain {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(16);
        RecursiveSummator summator = new RecursiveSummator(0, 1_000_000);
        final Long result = forkJoinPool.invoke(summator);
        System.out.println(result);
        long sum = 0;
        for (long i = 0; i <= 1_000_000; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
