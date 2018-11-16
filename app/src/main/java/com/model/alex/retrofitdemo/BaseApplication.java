package com.model.alex.retrofitdemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.base.baselibrary.rxretrofit.RetrofitFactory;
import com.base.baselibrary.rxretrofit.RxErrorHandler;
import com.base.baselibrary.utils.UiUtil;
import com.lzy.okgo.OkGo;

import java.util.logging.Level;

import io.reactivex.plugins.RxJavaPlugins;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.okgo.alex.okgodemo
 *  @文件名:   BaseApplication
 *  @创建者:   xuerui
 *  @创建时间:  2018/5/30 18:11
 *  @描述：    TODO
 */
public class BaseApplication extends Application {
	private static final String TAG = "BaseApplication";

	@Override
	public void onCreate() {
		super.onCreate();
		UiUtil.init(this);
		RetrofitFactory.setBaseUrl("http://192.168.5.185:8089/zxy-mobile-new/");
		EnumSingleton.INSTANCE.doSomeThing2();
		// 这两行必须写在init之前，否则这些配置在init过程中将无效
		ARouter.openLog();     // 打印日志
		ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
		ARouter.init(this); // 尽可能早，推荐在Application中初始化

		RxJavaPlugins.setErrorHandler(new RxErrorHandler());
		OkGo.getInstance().debug("OkGo", Level.INFO, true);
		OkGo.getInstance()
			.setConnectTimeout(15000)  //全局的连接超时时间
			.setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
			.setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS);   //全局的写入超时时间
	}
}
