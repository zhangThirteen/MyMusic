package com.mobiletrain.qqmusic.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class ThreadUtil {
    private static ExecutorService executorService;
//    public static ExecutorService executorService;
//
//    public static void execute(Runnable task) {
//        if (executorService == null) {
//            executorService = Executors.newFixedThreadPool(10);
//        }
//        executorService.submit(task);
//    }

    public static void execute(Runnable task){
        if (executorService==null) {
            executorService = Executors.newFixedThreadPool(10);
        }
        executorService.submit(task);
    }

}
