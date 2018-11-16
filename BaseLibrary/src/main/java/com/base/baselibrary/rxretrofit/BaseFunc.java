package com.base.baselibrary.rxretrofit;


import com.base.baselibrary.utils.TLog;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/*
 *  @文件名:   BaseFunc
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/8 15:55
 *  @描述：    BaseFunc
 */

public class BaseFunc<T> implements Function<BaseResult<T>,Observable<T>> {

	@Override
	public Observable<T> apply(BaseResult<T> result) {
		TLog.error("BaseResult = " + Thread.currentThread().getName());
		if (!"0000".equals(result.code)){
			return Observable.error(new BaseException());
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Observable.just(result.data);
	}
}
