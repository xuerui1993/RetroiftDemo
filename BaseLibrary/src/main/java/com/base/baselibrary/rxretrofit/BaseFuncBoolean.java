package com.base.baselibrary.rxretrofit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/*
 *  @文件名:   BaseFuncBoolean
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/11 10:37
 *  @描述：    Boolean类型转换封装
 */
public class BaseFuncBoolean<T> implements Function<BaseResult<T>,Observable<Boolean>> {
	@Override
	public Observable<Boolean> apply(BaseResult<T> result) {
		if ("0000".equals(result.code)){
			return Observable.error(new BaseException());
		}
		return Observable.just(true);
	}
}
