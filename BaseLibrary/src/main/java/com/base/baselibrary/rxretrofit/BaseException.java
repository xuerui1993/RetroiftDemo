package com.base.baselibrary.rxretrofit;

import java.io.IOException;

/*
 *  @文件名:   BaseException
 *  @创建者:   xuerui
 *  @创建时间:  2018/5/31 17:57
 *  @描述：    rxjava统一错误类
 */
public class BaseException extends IOException {
	int code;
	String msg;

	public BaseException() {
	}

	public BaseException(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
