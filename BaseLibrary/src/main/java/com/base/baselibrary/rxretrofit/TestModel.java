package com.base.baselibrary.rxretrofit;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.base.baselibrary.rxretrofit
 *  @文件名:   TestModel
 *  @创建者:   xuerui
 *  @创建时间:  2018/7/2 9:22
 *  @描述：    TODO
 */
public class TestModel {
	String id;
	String name;

	public TestModel(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TestModel{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
	}
}
