package com.base.baselibrary.rxretrofit;

import com.base.baselibrary.baseui.BaseView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/*
 *  @文件名:   BaseObserver
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/8 17:26
 *  @描述：    BaseObserver
 */
public abstract class BaseObserver<T>  implements Observer<T> {
	private BaseView mView;

	public BaseObserver(BaseView view) {
		mView = view;
	}

	@Override
	public void onSubscribe(Disposable d) {

	}

	@Override
	public void onError(Throwable e) {
//		TLog.error("e = " + e.toString());
		e.printStackTrace();
//		mView.hideLoading();
	}

	@Override
	public void onComplete() {
		mView.hideLoading();
	}
}
