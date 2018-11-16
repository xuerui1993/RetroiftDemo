package com.base.baselibrary.rxretrofit;


import android.support.v4.util.ArrayMap;

import com.base.baselibrary.utils.TLog;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *  @文件名:   RetrofitFactory
 *  @创建者:   xuerui
 *  @创建时间:  2018/5/31 10:02
 *  @描述：    Retrofit单例工厂类
 */
public class RetrofitFactory {
	private Interceptor mHeadInterceptor;
	private OkHttpClient mOkHttpClient;
	private Interceptor mParamsInterceptor;
	private Retrofit mRetrofit;
	private static String BASE_URL = "";
	private ArrayMap<String, String> mHeaderMap = new ArrayMap<>();
	private ArrayMap<String, String> mParamsMap = new ArrayMap<>();
	private HttpLoggingInterceptor mLogInterceptor;
	private static class SingletonHolder {
		private final static RetrofitFactory INSTANCE = new RetrofitFactory();
	}

	public static RetrofitFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public static void setBaseUrl(String baseUrl) {
		BASE_URL = baseUrl;
	}

	private RetrofitFactory() {
		TLog.error("11111");
		initRetrofit();
	}

	/**
	 * 清空原有的统一请求头后，重新添加统一请求头
	 * @param map  请求头的map
	 */
	public void addCommonHead(ArrayMap<String, String> map) {
		if (map == null) return;
		mHeaderMap.clear();
		Set<String> set = map.keySet();
		if (set.size() > 0) {
			for (String s : set) {
				mHeaderMap.put(s, map.get(s));
			}
		}
	}

	/**
	 * 清空原有的统一Params后，重新添加统一请求头
	 * @param map  Params的map
	 */
	public void addCommonParams(ArrayMap<String, String> map) {
		if (map == null) return;
		mParamsMap.clear();
		Set<String> set = map.keySet();
		if (set.size() > 0) {
			for (String s : set) {
				mParamsMap.put(s, map.get(s));
			}
		}
	}

	/**
	 * 添加统一的Params
	 * @param map Params的map的map
	 * @param append  是否追加，如果是true,代表追加
	 */
	public void addCommonParams(ArrayMap<String, String> map, boolean append) {
		if (map == null) return;
		if (!append) {
			mParamsMap.clear();
		}
		Set<String> set = map.keySet();
		if (set.size() > 0) {
			for (String s : set) {
				mParamsMap.put(s, map.get(s));
			}
		}
	}


	/**
	 * 添加统一的请求头
	 * @param map 请求头的map
	 * @param append  是否追加，如果是true,代表追加
	 */
	public void addCommonHead(ArrayMap<String, String> map, boolean append) {
		if (map == null) return;
		if (!append) {
			mHeaderMap.clear();
		}
		Set<String> set = map.keySet();
		if (set.size() > 0) {
			for (String s : set) {
				mHeaderMap.put(s, map.get(s));
			}
		}
	}

	/**
	 * 初始化Retrofit
	 */
	private void initRetrofit() {
		mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.client(initClient())
			.build();
	}

	/**
	 * 修改Retrofit base_url
	 */
	public void resetBaseUrl(String base_url) {
		BASE_URL = base_url;
		mRetrofit = mRetrofit.newBuilder().baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.client(mOkHttpClient)
			.build();
	}

	/**
	 * 初始化OkHttpClient
	 */
	private OkHttpClient initClient() {
		initHeadInterceptor();
		initParamsInterceptor();
		initLogInterceptor();
		mOkHttpClient = new OkHttpClient.Builder().
			addInterceptor(mHeadInterceptor)
			.addInterceptor(mParamsInterceptor)
			.addInterceptor(mLogInterceptor)
			.connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(10, TimeUnit.SECONDS)
			.build();
		return mOkHttpClient;
	}

	private void initParamsInterceptor() {
		mParamsInterceptor = chain -> {
			Request oldRequest = chain.request();
			HttpUrl.Builder requestBuilder = chain.request().url().newBuilder();
			Set<String> set = mParamsMap.keySet();
			if (set.size() > 0) {
				for (String key : set) {
					requestBuilder.addQueryParameter(key, mParamsMap.get(key));
				}
			}
			Request newRequest = oldRequest.newBuilder()
				.method(oldRequest.method(), oldRequest.body())
				.url(requestBuilder.build())
				.build();
			return chain.proceed(newRequest);
		};
	}

	/**
	 * 初始化请求头拦截器
	 */
	private void initHeadInterceptor() {
		mHeadInterceptor = chain -> {
			Request.Builder requestBuilder = chain.request().newBuilder();
			Set<String> set = mHeaderMap.keySet();
			if (set.size() > 0) {
				for (String key : set) {
					requestBuilder.addHeader(key, mHeaderMap.get(key));
				}
			}
			Request request = requestBuilder.build();
			return chain.proceed(request);
		};
	}

	/**
	 * 设置是否debug模式
	 * isDebug 为true时不打印日志
	 */
	public void setDebug(boolean isDebug) {
		mLogInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
	}

	/**
	 * 初始化日志拦截器
	 */
	private void initLogInterceptor() {
		mLogInterceptor = new HttpLoggingInterceptor();
		mLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
	}

	/**
	 * 具体服务实例化
	 */
	public <T> T create(Class<T> t) {
		return mRetrofit.create(t);
	}

}
