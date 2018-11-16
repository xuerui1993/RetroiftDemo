package com.base.baselibrary.baseui;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.base.baselibrary.utils.TLog;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/*
 *  @项目名：  RetrofitDemo
 *  @包名：    com.base.baselibrary.baseui
 *  @文件名:   RxAppActivity
 *  @创建者:   xuerui
 *  @创建时间:  2018/7/2 16:38
 *  @描述：    TODO
 */
public class RxAppActivity extends AppCompatActivity implements LifecycleProvider<ActivityEvent> {
	private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

	@Override
	@NonNull
	@CheckResult
	public final Observable<ActivityEvent> lifecycle() {
		return lifecycleSubject.hide();
	}

	@Override
	@NonNull
	@CheckResult
	public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
		return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
	}

	@Override
	@NonNull
	@CheckResult
	public final <T> LifecycleTransformer<T> bindToLifecycle() {
		return RxLifecycleAndroid.bindActivity(lifecycleSubject);
	}

	@Override
	@CallSuper
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lifecycleSubject.onNext(ActivityEvent.CREATE);
	}

	@Override
	@CallSuper
	protected void onStart() {
		super.onStart();
		lifecycleSubject.onNext(ActivityEvent.START);
	}

	@Override
	@CallSuper
	protected void onResume() {
		super.onResume();
		lifecycleSubject.onNext(ActivityEvent.RESUME);
	}

	@Override
	@CallSuper
	protected void onPause() {
		lifecycleSubject.onNext(ActivityEvent.PAUSE);
		super.onPause();
	}

//	@Override
//	@CallSuper
//	protected void onStop() {
//		lifecycleSubject.onNext(ActivityEvent.STOP);
//		super.onStop();
//	}

	@Override
	@CallSuper
	protected void onDestroy() {
		TLog.error("aaaa");
		lifecycleSubject.onNext(ActivityEvent.DESTROY);
		super.onDestroy();
	}
}
