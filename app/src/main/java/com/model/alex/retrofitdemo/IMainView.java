package com.model.alex.retrofitdemo;

import com.base.baselibrary.baseui.BaseView;
import com.trello.rxlifecycle2.LifecycleProvider;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.okgo.alex.okgodemo
 *  @文件名:   IMainView
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/19 10:18
 *  @描述：    TODO
 */
interface IMainView extends BaseView {
	LifecycleProvider<?>  getLifecycleFormer();
}
