package com.model.alex.retrofitdemo;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.okgo.alex.okgodemo
 *  @文件名:   CardBean
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/8 16:37
 *  @描述：    TODO
 */
public class CardBean {

	/**
	 * catId : -1
	 * catName : 全部
	 */

	private String catId;
	private String catName;

	public String getCatId() { return catId;}

	public void setCatId(String catId) { this.catId = catId;}

	public String getCatName() { return catName;}

	public void setCatName(String catName) { this.catName = catName;}

	@Override
	public String toString() {
		return "CardBean{" + "catId='" + catId + '\'' + ", catName='" + catName + '\'' + '}';
	}
}
