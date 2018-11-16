package com.model.alex.retrofitdemo;

/*
 *  @项目名：  RetrofitDemo
 *  @包名：    com.model.alex.retrofitdemo
 *  @文件名:   DownloadListener
 *  @创建者:   xuerui
 *  @创建时间:  2018/10/19 17:22
 *  @描述：    TODO
 */
public interface DownloadListener {
	void onStart();

	void onProgress(int currentLength);

	void onFinish(String localPath);

	void onFailure(Throwable t);
}
