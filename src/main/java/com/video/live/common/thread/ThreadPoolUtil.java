package com.video.live.common.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/11/26 15:34
 */
public class ThreadPoolUtil {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);

    //private static final int CORE_POOL_SIZE = (int) (Runtime.getRuntime().availableProcessors() / (1 - 0.5f));
    private static final int CORE_POOL_SIZE = 25;
    private static final int MAX_POOL_SIZE = 35;
    private static final long KEEP_LIVE_TIME = 60L;
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new LinkedBlockingQueue<>();
    private static final ThreadPoolExecutor POOL;

    static {
        POOL = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_LIVE_TIME,
                TimeUnit.MILLISECONDS,
                BLOCKING_QUEUE,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public static void execute(Runnable runnable) {
        POOL.execute(runnable);
    }

    public static void shutDown() {
        boolean shutdown = POOL.isShutdown();
        if (!shutdown) {
            POOL.shutdown();
        }
    }

    public static void printQueue() {
        for (Runnable runnable : BLOCKING_QUEUE) {
            System.out.println(runnable);
        }
    }

    public static void shutDownNow() {
        POOL.shutdownNow();
    }

    public static void remove(Runnable runnable) {
        BLOCKING_QUEUE.remove(runnable);
    }
}
