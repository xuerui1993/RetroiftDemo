package com.model.alex.retrofitdemo;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.base.baselibrary.baseui.BaseMvpActivity;
import com.base.baselibrary.rxretrofit.BaseObserver;
import com.base.baselibrary.rxretrofit.RetrofitFactory;
import com.base.baselibrary.rxretrofit.TestModel;
import com.base.baselibrary.utils.TLog;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/app/main")
public class MainActivity extends BaseMvpActivity<MainPresenterImpl> implements IMainView {

	private static final String TAG = "MainActivity";
	@BindView(R.id.tv4)
	TextView mTv4;
	@BindView(R.id.tv3)
	TextView mTv3;
	@BindView(R.id.wv)
	Html5WebView mWebView;
	private ListView mListview;

	@Override
	protected int getLayoutRes() {
		return R.layout.activity_main;
	}

	@Override
	protected void initMvpContent() {
//		PermissionUtils.requestPermissions(this, PermissionUtils.RequestCode.EXTERNAL, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionUtils.OnPermissionListener() {
//			@Override
//			public void onPermissionGranted() {
//				OkGo.get("http://axatp.zhixueyun.com//static//http//00//0E//Ci8zWFvG8VuEOg7DAAAAAHqAqFs194.mp3")//
//					.headers("Connection","close")
//					.tag(this)//
//					.execute(new FileCallback("1111.mp3") {  //文件下载时，可以指定下载的文件目录和文件名
//
//						@Override
//						public void onBefore(BaseRequest request) {
//							super.onBefore(request);
//						}
//
//						@Override
//						public void onSuccess(File file, Call call, Response response) {
//							// file 即为文件数据，文件保存在指定目录
//							TLog.log("下载成功");
//
//						}
//
//						@Override
//						public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//							TLog.log("下载中。。progress"+progress+",totalSize="+totalSize+",currentSize="+currentSize);
//						}
//
//						@Override
//						public void onError(Call call, Response response, Exception e) {
//							super.onError(call, response, e);
//							TLog.log("e="+e.toString());
//						}
//					});
//
//			}
//
//			@Override
//			public void onPermissionDenied(String[] deniedPermissions, boolean alwaysDenied) {
//
//			}
//		});

//		mWebView.loadUrl("http://v.zhixueyun.com/mstatic/http/unzip-content/01/36/Chr4gFtJVIaEGqFPAAAAACX9fbg029" +
//			"/index" +
//			".html");
		initView();
		mTv3.setText("嘻嘻");
		Observable
			.interval(1000,1000, TimeUnit.MILLISECONDS)
			.doOnSubscribe(new Consumer<Disposable>() {
				@Override
				public void accept(Disposable disposable) throws Exception {

				}
			})
			.subscribe(new Observer<Long>() {
				@Override
				public void onSubscribe(Disposable d) {

				}

				@Override
				public void onNext(Long aLong) {

				}

				@Override
				public void onError(Throwable e) {

				}

				@Override
				public void onComplete() {

				}
			});
		Observable.create(new ObservableOnSubscribe<Object>() {
			@Override
			public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

			}
		})
		.map(new Function<Object, Object>() {
			@Override
			public Object apply(Object o) throws Exception {
				return null;
			}
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe(new Observer<Object>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onNext(Object o) {

			}

			@Override
			public void onError(Throwable e) {

			}

			@Override
			public void onComplete() {

			}
		});

		DownloadUtil util = new DownloadUtil();
		util.downloadFile("http://axatp.zhixueyun.com/static/http/00/0E/Ci8zWFvIL9KEObYdAAAAAE9S1Fo271.mp3", new DownloadListener() {
			@Override
			public void onStart() {

			}

			@Override
			public void onProgress(int currentLength) {
				TLog.log("currentLength = " +currentLength);
			}

			@Override
			public void onFinish(String localPath) {
				TLog.log("localPath = " +localPath);
			}

			@Override
			public void onFailure(Throwable t) {
				TLog.log("Throwable = " +t.toString());
			}
		});


	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

	public LifecycleProvider<?> getLifecycleFormer() {
		return this;
	}

	private void initView() {
		mListview = findViewById(R.id.listview);

	}

	@Override
	protected MainPresenterImpl getPresenter() {
		return PresenterFactory.getInstance().getMainPresenter(this);
	}

	@OnClick({R.id.tv4, R.id.tv3})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.tv4:
				break;
			case R.id.tv3:
				//				throw new RuntimeException("XY");
				RetrofitFactory.getInstance().resetBaseUrl("http://baidu/");

				NetApi.test(this).subscribe(new BaseObserver<List<TestModel>>(this) {
					@Override
					public void onNext(List<TestModel> testModels) {
						//				testModels = null;
						//				testModels.add(new TestModel("11",""));
						TLog.error("testModels = " + Thread.currentThread().getName() + testModels);
						//										throw new RuntimeException("afasdf");

					}

					@Override
					public void onError(Throwable e) {
						super.onError(e);
					}
				});
				break;
		}
	}


}