package com.base.baselibrary.rxretrofit;

import com.base.baselibrary.utils.DeviceUtils;
import com.base.baselibrary.utils.ToastUtil;
import com.base.baselibrary.utils.UiUtil;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *  @项目名：  RetrofitDemo
 *  @包名：    com.base.baselibrary.rxretrofit
 *  @文件名:   RxSchedulers
 *  @创建者:   xuerui
 *  @创建时间:  2018/7/2 14:04
 *  @描述：    TODO
 */
public class RxSchedulers {
	public static <T> ObservableTransformer<T, T> compose() {
		return new ObservableTransformer<T, T>() {
			@Override
			public ObservableSource<T> apply(Observable<T> observable) {
				return observable.subscribeOn(Schedulers.io()).doOnSubscribe(disposable -> {
					if (!DeviceUtils.isNetworkConnected(UiUtil.getContext())) {
						ToastUtil.showNetErrorToast();
					}
				}).observeOn(AndroidSchedulers.mainThread());
			}
		};
	}

	public static <T> ObservableTransformer<BaseResult<T>, T> compose(LifecycleProvider<?> p) {
		return new ObservableTransformer<BaseResult<T>, T>() {
			@Override
			public ObservableSource<T> apply(Observable<BaseResult<T>> observable) {
				return observable.flatMap(new BaseFunc<>()).subscribeOn(Schedulers.io()).doOnSubscribe(disposable -> {
					if (!DeviceUtils.isNetworkConnected(UiUtil.getContext())) {
						ToastUtil.showNetErrorToast();
					}
				}).compose(p.bindToLifecycle()).observeOn(AndroidSchedulers.mainThread());
			}
		};
	}

}
