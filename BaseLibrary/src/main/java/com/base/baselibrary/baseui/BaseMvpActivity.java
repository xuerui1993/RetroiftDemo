package com.base.baselibrary.baseui;

import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.subjects.BehaviorSubject;

/*
 *  @文件名:   BaseMvpActivity
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/12 9:48
 *  @描述：    TODO
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BasicActivity implements BaseView{
	private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();
	public T mPresenter;

	@Override
	protected void initContent() {
		mPresenter = getPresenter();
		initMvpContent();
	}

	protected abstract void initMvpContent();

	protected abstract T getPresenter();


	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void onError(String msg) {
		toast(msg);
	}

	public BehaviorSubject<ActivityEvent> getLifecycleSubject(){
		return lifecycleSubject;
	}
}
