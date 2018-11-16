package com.model.alex.retrofitdemo;

import com.base.baselibrary.baseui.BasePresenter;
import com.base.baselibrary.rxretrofit.BaseObserver;

import java.util.List;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.okgo.alex.okgodemo
 *  @文件名:   MainPresenterImpl
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/12 10:39
 *  @描述：    TODO
 */
public class MainPresenterImpl extends BasePresenter implements MainPresenter{
	IMainView mView;

	public MainPresenterImpl(IMainView mainView) {
		mView = mainView;
	}

	@Override
	public void login() {
		NetApi.register(mView.getLifecycleFormer())
			.subscribe(new BaseObserver<List<CardBean>>(mView) {
			@Override
			public void onNext(List<CardBean> cardBeans) {

			}
		});
	}
}
