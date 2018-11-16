package com.base.baselibrary.utils;

import android.app.Application;
import android.os.Handler;
import android.support.annotation.NonNull;


/**
 * 获取context,提交任务，读取string。。。。
 */
public class UiUtil {
    /**
     * 资讯页面是否处于刷新状态
     * true为加载更多中，false为不处于。
     */
    public static boolean IS_LOADING_MORE = false;
    private static Application context;
    private static Handler mHandler;

    public static Application getContext() {
        return context;
    }

    public static void init(Application gpApplication) {
        //application是context的子类
        context = gpApplication;
//        TextView tv = new TextView(gpApplication);
//        context.startActivity(new Intent(context,AbsActivity.class));
        //添加handler,线程间通信 hanler可以做成静态唯一的
        mHandler = new Handler();
    }

    /**
     * 提交一个任务
     */
    public static void post(Runnable task){
        mHandler.post(task);
    }

    /**
     * 延时提交一个任务
     */
    public static void postDelayed(Runnable task, long delay){
        mHandler.postDelayed(task, delay);
    }

    /**
     * 取消一个任务
     */
    public static void cancel(Runnable task) {
        mHandler.removeCallbacks(task);
    }

    /**
     * 取消所有任务
     */
    public static void removeAllMessage() {
        mHandler.removeCallbacksAndMessages(null);
    }

    @NonNull
    public static String[] getStringArray(int resId){
        return context.getResources().getStringArray(resId);
    }

    /**
     * 获取带占位符的string,
     * @param resId
     * @return
     */
    @NonNull
    public static String getString(int resId, Object... formatArgs){
        return context.getResources().getString(resId, formatArgs);
    }


}
