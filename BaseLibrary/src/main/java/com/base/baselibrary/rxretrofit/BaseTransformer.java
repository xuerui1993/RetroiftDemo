package com.base.baselibrary.rxretrofit;

import com.base.baselibrary.utils.DeviceUtils;
import com.base.baselibrary.utils.ToastUtil;
import com.base.baselibrary.utils.UiUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *  @文件名:   BaseTransformer
 *  @创建者:   xuerui
 *  @创建时间:  2018/7/2 15:53
 *  @描述：    RxJava线程管理和生命周期绑定类
 */
public class BaseTransformer<T> implements ObservableTransformer<BaseResult<T>, T> {
	LifecycleProvider<ActivityEvent> mProvider;
	/**
	 * 在子线程执行网络请求后回调到主线程，并绑定Activity生命周期
	 * 当 mProvider 为空时，不绑定Activity生命周期
	 */
	public BaseTransformer() {
	}

	public BaseTransformer(LifecycleProvider<ActivityEvent> provider) {
		mProvider = provider;
	}

	@Override
	public ObservableSource<T> apply(Observable<BaseResult<T>> observable) {
		Observable<T> newObservable = observable
			.flatMap(new BaseFunc<>()).subscribeOn(Schedulers.io()).doOnSubscribe(disposable -> {
			if (!DeviceUtils.isNetworkConnected(UiUtil.getContext())) {
				ToastUtil.showNetErrorToast();
			}
		}).observeOn(AndroidSchedulers.mainThread());
		if (mProvider!=null){
			newObservable = newObservable
				.compose(mProvider.bindUntilEvent(ActivityEvent.DESTROY));
		}
		return newObservable;
	}
}
