package org.esurovskiy;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ListSummatorTask extends RecursiveTask<Long> {
    private List<Long> list;

    public ListSummatorTask(final List<Long> list) {
        this.list = list;
    }

    @Override
    protected Long compute() {
        if (list.size() <= 100) {
            long sum = 0;
            for (final Long e : list) {
                sum += e;
            }
            return sum;
        } else {
            int size = list.size();
            ListSummatorTask first =
                    new ListSummatorTask(list.subList(0, size / 2));
            first.fork();
            ListSummatorTask second =
                    new ListSummatorTask(list.subList(size / 2 , size));
            Long secondResult = second.compute();
            return secondResult + first.join();
        }
    }
}
