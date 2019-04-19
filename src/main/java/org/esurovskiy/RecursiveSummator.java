package org.esurovskiy;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


public class RecursiveSummator extends RecursiveTask<Long> {
    private long from;
    private long to;

    public RecursiveSummator(final long from, final long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        if ((to - from <= 1_000)) {
            long sum = 0;
            for (long i = from; i <= to; i++) {
                sum += i;
            }
            System.out.println(Thread.currentThread().getName()
                    + " Compute sum between "
                    + from + " and " + to + " = " + sum);
            return sum;
        } else {

            long mid = (to + from) / 2;
            System.out.println(
                    Thread.currentThread().getName() +
                    " Forking on 2 tasks. First: "
                    + from + " to " + mid +
                    "Second: " + mid + 1 + " to " + to);
            RecursiveSummator firstHalf
                    = new RecursiveSummator(from, mid);
            RecursiveSummator secondHalf
                    = new RecursiveSummator(mid + 1, to);
            ForkJoinTask<Long> fork = firstHalf.fork();
            final Long compute = secondHalf.compute();
            return fork.join() + compute;

        }
    }
}
