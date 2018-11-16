package com.model.alex.retrofitdemo;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.okgo.alex.okgodemo
 *  @文件名:   PresenterFactory
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/19 9:18
 *  @描述：    TODO
 */
public class PresenterFactory {
	private PresenterFactory() {}

	private static class PresenterFactoryHolder{
		private static final PresenterFactory INSTANCE = new PresenterFactory();
	}

	public static PresenterFactory getInstance(){
		return PresenterFactoryHolder.INSTANCE;
	}

	public MainPresenterImpl getMainPresenter(MainActivity activity){
		return new MainPresenterImpl(activity);
	}

}
