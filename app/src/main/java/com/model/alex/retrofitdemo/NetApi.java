package com.model.alex.retrofitdemo;

import com.base.baselibrary.rxretrofit.BaseFunc;
import com.base.baselibrary.rxretrofit.RetrofitFactory;
import com.base.baselibrary.rxretrofit.TestModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;


/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.okgo.alex.okgodemo
 *  @文件名:   NetApi
 *  @创建者:   xuerui
 *  @创建时间:  2018/5/31 11:21
 *  @描述：    TODO
 */
public class NetApi {
	private static final String TAG = "NetApi";

	public static Observable<List<CardBean>> register(LifecycleProvider<?> p){
		return RetrofitFactory.getInstance().create(NetRepository.class).register()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread());
	}

	public static Observable<List<TestModel>> test(LifecycleProvider<?> p){
		return RetrofitFactory.getInstance().create(NetRepository.class).testModel()
			.flatMap(new BaseFunc<>())
			.subscribeOn(Schedulers.io())
			.compose(p.bindToLifecycle())
			.observeOn(AndroidSchedulers.mainThread());
	}


	public static Call<ResponseBody> downloadFile(String url){
		return RetrofitFactory.getInstance().create(NetRepository.class).downloadFile(url);
	}

}
