package com.base.baselibrary.baseui;

import io.reactivex.subjects.BehaviorSubject;

/*
 *  @文件名:   BaseView
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/12 9:59
 *  @描述：    View层基类接口
 */
public interface BaseView {
	void showLoading();
	void hideLoading();
	void onError(String msg);
	BehaviorSubject<?> getLifecycleSubject();
}
