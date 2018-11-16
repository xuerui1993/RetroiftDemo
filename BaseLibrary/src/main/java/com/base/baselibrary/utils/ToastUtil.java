package com.base.baselibrary.utils;

/*
 *  @文件名:   ToastUtil
 *  @创建者:   xuerui
 *  @创建时间:  2017/9/20 14:11
 *  @描述：    土司工具
 */

import android.content.Context;
import android.widget.Toast;
import com.base.baselibrary.R;

public class ToastUtil {
	private static String oldMsg;
	private static Toast toast;
	private static long oneTime;
	private static long twoTime;

	/**
	 * 网络连接错误
	 */
	public static void showNetErrorToast(){
		showToast(UiUtil.getContext(),UiUtil.getString(R.string.net_error));
	}

	/**
	 * 没有更多了
	 */
	public static void showNoMoreContentToast(){
		showToast(UiUtil.getContext(),UiUtil.getString(R.string.no_more_content));
	}

	/**
	 * 弹一个土司
	 * @param s 显示的内容
	 */
	public static void showToast(String s) {
		showToast(UiUtil.getContext(),s);
	}

	/**
	 * 显示Toast
	 * @param context
	 * @param message
	 */
	private static void showToast(Context context, String message) {
		if (message == null) return;
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			toast.show();
			oneTime = System.currentTimeMillis();
		} else {
			twoTime = System.currentTimeMillis();
			if (message.equals(oldMsg)) {
				if (twoTime - oneTime > Toast.LENGTH_SHORT) {
					toast.show();
				}
			} else {
				oldMsg = message;
				toast.setText(message);
				toast.show();
			}
		}
		oneTime = twoTime;
	}

}
