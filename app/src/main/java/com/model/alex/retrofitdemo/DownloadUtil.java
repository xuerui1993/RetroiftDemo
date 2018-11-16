package com.model.alex.retrofitdemo;

import android.os.Environment;
import android.util.Log;

import com.base.baselibrary.utils.TLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 *  @项目名：  RetrofitDemo
 *  @包名：    com.model.alex.retrofitdemo
 *  @文件名:   DownloadUtil
 *  @创建者:   xuerui
 *  @创建时间:  2018/10/19 17:24
 *  @描述：    TODO
 */
public class DownloadUtil {
	private static final String TAG = "DownloadUtil";
	private static final String PATH_CHALLENGE_VIDEO = Environment.getExternalStorageDirectory() + "/DownloadFile";
	//视频下载相关
	private Call<ResponseBody> mCall;
	private File mFile;
	private Thread mThread;


	public void downloadFile(String url, final DownloadListener downloadListener) {
		String name = url;
		//通过Url得到文件并创建本地文件
		mFile = FileUtil.getSdCardFile("111.mp3");
		//建立一个文件
		mCall = NetApi.downloadFile(url);
		mCall.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
				//下载文件放在子线程
				TLog.log("11111");
				//保存到本地
				writeFile2Disk(response, mFile, downloadListener);
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				downloadListener.onFailure(t); //下载失败
			}
		});

	}

	//将下载的文件写入本地存储
	private void writeFile2Disk(Response<ResponseBody> response, File file, DownloadListener downloadListener) {
		downloadListener.onStart();
		long currentLength = 0;
		OutputStream os = null;

		InputStream is = response.body().byteStream(); //获取下载输入流
		long totalLength = response.body().contentLength();

		try {
			os = new FileOutputStream(file); //输出流
			int len;
			byte[] buff = new byte[1024];
			while ((len = is.read(buff)) != -1) {
				TLog.log("11111");
				os.write(buff, 0, len);
				currentLength += len;
				Log.e(TAG, "当前进度: " + currentLength);
				//计算当前下载百分比，并经由回调传出
				downloadListener.onProgress((int) (100 * currentLength / totalLength));
				//当百分比为100时下载结束，调用结束回调，并传出下载后的本地路径
				if ((int) (100 * currentLength / totalLength) == 100) {
					downloadListener.onFinish(mFile.getAbsolutePath()); //下载完成
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close(); //关闭输出流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close(); //关闭输入流
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
