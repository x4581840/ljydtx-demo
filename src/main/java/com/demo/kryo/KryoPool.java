package com.demo.kryo;

import com.esotericsoftware.kryo.Kryo;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;


class KryoPool {
    private final LinkedBlockingQueue<Kryo> queue;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(KryoPool.class);

    KryoPool(int capacity, Supplier<Kryo> factory) {
        final LinkedBlockingQueue<Kryo> qtmp = new LinkedBlockingQueue(capacity);
        for (int i = 0; i < capacity; i++) {
            qtmp.offer(factory.get());
        }

        queue = qtmp;
    }

    public Kryo obtain() {
        try {
            return queue.take();
        } catch (Exception e) {
            logger.error("KryoPool", e);
            return null;
        }
    }

    public void free(Kryo kryo) {
        try {
            queue.put(kryo);
        } catch (Exception e) {
            logger.error("KryoPool", e);
        }
    }
}
