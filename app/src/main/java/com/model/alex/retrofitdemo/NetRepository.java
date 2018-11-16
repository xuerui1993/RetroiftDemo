package com.model.alex.retrofitdemo;

import com.base.baselibrary.rxretrofit.BaseResult;
import com.base.baselibrary.rxretrofit.TestModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.okgo.alex.okgodemo
 *  @文件名:   NetRepository
 *  @创建者:   xuerui
 *  @创建时间:  2018/5/31 11:37
 *  @描述：    TODO
 */
public interface NetRepository {
	/*
	   用户注册
	*/
	@GET("product/getProductCategory3")
	Observable<List<CardBean>> register();

	@GET("product/getProductCategory")
	Observable<BaseResult<List<CardBean>>> test(@Query("str") String str);

	@GET("user/test")
	Observable<BaseResult<List<TestModel>>> testModel();

	@Streaming //大文件时要加不然会OOM
	@GET
	Call<ResponseBody> downloadFile(@Url String fileUrl);
}
