package com.base.baselibrary.rxretrofit;

/*
 *  @文件名:   BaseResult
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/8 15:58
 *  @描述：    TODO
 */
public class BaseResult<T> {
	String code;
	String msg;
	T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getT() {
		return data;
	}

	public void setT(T resultData) {
		this.data = resultData;
	}

	@Override
	public String toString() {
		return "BaseResult{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
	}
}
