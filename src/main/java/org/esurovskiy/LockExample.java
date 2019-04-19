package org.esurovskiy;

import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO: write javadoc
 *
 * @author Evgeniy Surovskiy
 */
public class LockExample {
    ReentrantLock lock = new ReentrantLock();
    long time;

    void method1() {
        final boolean b = lock.tryLock();
        if (b) {
            time = System.currentTimeMillis();
            lock.unlock();
        }

        //.....

    }
}
