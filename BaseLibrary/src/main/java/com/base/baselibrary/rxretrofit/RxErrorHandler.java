package com.base.baselibrary.rxretrofit;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import io.reactivex.functions.Consumer;

/*
 *  @项目名：  RetrofitDemo
 *  @包名：    com.base.baselibrary.rxretrofit
 *  @文件名:   RxErrorHandler
 *  @创建者:   xuerui
 *  @创建时间:  2018/7/2 14:54
 *  @描述：    TODO
 */
public class RxErrorHandler implements Consumer<Throwable> {
	private static final String TAG = "RxErrorHandler";

	@Override
	public void accept(Throwable throwable) throws Exception {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw =  new PrintWriter(sw);
			//将出错的栈信息输出到printWriter中
			throwable.printStackTrace(pw);
			pw.flush();
			sw.flush();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		Log.e(TAG, sw.toString());
	}
}
