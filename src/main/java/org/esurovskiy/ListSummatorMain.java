package org.esurovskiy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ListSummatorMain {
    private static int SIZE = 10_000;

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        final Random rnd = new Random();
        for (int i = 0; i < SIZE; i++) {
            list.add((long) rnd.nextInt(100));
        }

        long sum = 0;
        for (final Long e : list) {
            sum += e;
        }
        System.out.println("Sum in simple way = " + sum);

        final ForkJoinPool pool = new ForkJoinPool();
        final Long result = pool.invoke(new ListSummatorTask(list));
        System.out.println("Result from FJP " + result);
    }
}
