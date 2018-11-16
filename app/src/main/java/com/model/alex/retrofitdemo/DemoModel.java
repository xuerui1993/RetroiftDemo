package com.model.alex.retrofitdemo;

import java.util.List;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.okgo.alex.okgodemo
 *  @文件名:   DemoModel
 *  @创建者:   xuerui
 *  @创建时间:  2018/5/30 18:14
 *  @描述：    TODO
 */
public class DemoModel {

	/**
	 * code : 0
	 * msg : 成功
	 * resultData : [{"catId":"-1","catName":"全部"},{"catId":"2000102","catName":"生命健康"},{"catId":"2000103","catName":"居家安心"},{"catId":"2000105","catName":"出行顺利"}]
	 */

	private int code;
	private String msg;
	private List<ResultDataBean> resultData;

	public int getCode() { return code;}

	public void setCode(int code) { this.code = code;}

	public String getMsg() { return msg;}

	public void setMsg(String msg) { this.msg = msg;}

	public List<ResultDataBean> getResultData() { return resultData;}

	public void setResultData(List<ResultDataBean> resultData) { this.resultData = resultData;}

	public static class ResultDataBean {
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
			return "ResultDataBean{" + "catId='" + catId + '\'' + ", catName='" + catName + '\'' + '}';
		}
	}


	@Override
	public String toString() {
		return "DemoModel{" + "code=" + code + ", msg='" + msg + '\'' + ", resultData=" + resultData + '}';
	}
}
