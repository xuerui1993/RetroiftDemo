package com.model.alex.retrofitdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.http.SslError;
import android.os.Message;
import android.util.AttributeSet;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


public class Html5WebView extends WebView {

	public static final String TAG = "Html5WebView";
	private ProgressBar mProgressBar;
	//    boolean isNotTop = true;
	private Context mContext;
	private WebsiteChangeListener mWebsiteChangeListener;
	private boolean mErrorState;
	private String mFailingUrl;
	private LoadProcessListener processListener;

	public Html5WebView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public Html5WebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public Html5WebView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		init();
	}

	private void init() {
		//添加错误view

		//================顶部进度条的初始化===================
		mProgressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleHorizontal);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 6);
		mProgressBar.setLayoutParams(layoutParams);

		Drawable drawable = mContext.getResources().getDrawable(R.drawable.web_progress_bar_states);
		mProgressBar.setProgressDrawable(drawable);
		addView(mProgressBar);
		//================顶部进度条的初始化===================

		WebSettings mWebSettings = getSettings();
		mWebSettings.setSupportZoom(true);
		mWebSettings.setLoadWithOverviewMode(true);
		mWebSettings.setUseWideViewPort(true);
		mWebSettings.setDefaultTextEncodingName("utf-8");
		mWebSettings.setLoadsImagesAutomatically(true);

		//调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
		mWebSettings.setJavaScriptEnabled(true);
		mWebSettings.setSupportMultipleWindows(true);
		//缓存数据
		saveData(mWebSettings);
		newWin(mWebSettings);
		setWebChromeClient(webChromeClient);
		setWebViewClient(webViewClient);//原来的设置。
		//        setWebViewClient(new NoAdWebViewClient(mContext));//去除广告，可是不起作用
	}




	/**
	 *获取网页加载的错误状态
	 * @return true为错误状态 ,false为正常状态
	 */
	public boolean getWebErrorState() {
		return mErrorState;
	}

	/**
	 * 多窗口的问题
	 */
	private void newWin(WebSettings mWebSettings) {
		//html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
		//然后 复写 WebChromeClient的onCreateWindow方法
		mWebSettings.setSupportMultipleWindows(true);
		mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
	}

	/**
	 * HTML5数据存储
	 */
	private void saveData(WebSettings mWebSettings) {
		//有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置

		if (NetStatusUtil.isConnected(mContext)) {
			mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
		} else {
			mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载
		}

		mWebSettings.setDomStorageEnabled(true);
		mWebSettings.setDatabaseEnabled(true);
		mWebSettings.setAppCacheEnabled(true);
		String appCachePath = mContext.getCacheDir().getAbsolutePath();
		mWebSettings.setAppCachePath(appCachePath);
	}

	public boolean needClearHistory;

	/**
	 * 配合onPagerFinish方法清楚历史记录和清楚缓存
	 */
	public void clearHistoryAndClearCache(){
		needClearHistory = true;
		clearCache(true);
	}

	WebViewClient webViewClient = new WebViewClient() {
		/**
		 * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
		 */
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url == null) return false;
			view.loadUrl(url);

			if (mWebsiteChangeListener != null) {
				mWebsiteChangeListener.onUrlChange(url);
			}
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			mFailingUrl = url;
			if (processListener != null) {
				processListener.onLoadPageStart(view, url, favicon);
			}
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			if (needClearHistory) {
				needClearHistory = false;
				view.clearHistory();//清除历史记录
			}

			if (processListener != null) {
				processListener.onLoadPageFinish(view, url);
			}
		}

		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			mFailingUrl = failingUrl;

			if (processListener != null) {
				processListener.onLoadError(view, errorCode, description, failingUrl);
			}
		}

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
			super.onReceivedSslError(view, handler, error);
			handler.proceed();
		}
	};

	WebChromeClient webChromeClient = new WebChromeClient() {

		//=========HTML5定位==========================================================
		//需要先加入权限
		//<uses-permission android:name="android.permission.INTERNET"/>
		//<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
		//<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
		@Override
		public void onReceivedIcon(WebView view, Bitmap icon) {
			super.onReceivedIcon(view, icon);
		}

		@Override
		public void onGeolocationPermissionsHidePrompt() {
			super.onGeolocationPermissionsHidePrompt();
		}

		@Override
		public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
			callback.invoke(origin, true, false);//注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
			super.onGeolocationPermissionsShowPrompt(origin, callback);
		}
		//=========HTML5定位==========================================================


		//=========多窗口的问题==========================================================
		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
			HitTestResult result = view.getHitTestResult();
			String data = result.getExtra();
			view.loadUrl(data);
			return true;
		}
		//=========多窗口的问题==========================================================

		//=========顶部进度条的进度更新===============================
		@Override
		public void onProgressChanged(WebView view, int newProgress) {

			if (newProgress == 100) {
				mProgressBar.setVisibility(GONE);
				String title = getTitle();
				//                isNotTop = true;
			} else {
				if (mProgressBar.getVisibility() == GONE) mProgressBar.setVisibility(VISIBLE);
				//                if (isNotTop && mProgressBar.getVisibility() == VISIBLE) {
				//                    if (mWebsiteChangeListener != null) {
				//                        mWebsiteChangeListener.onWebsiteChangeBackTop();
				//                        isNotTop = false;
				//                    }
				//                }
				mProgressBar.setProgress(newProgress);
			}
			super.onProgressChanged(view, newProgress);
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
			if (mWebsiteChangeListener != null) {
				mWebsiteChangeListener.onWebsiteChange(title);
			}
		}
	};

	public interface WebsiteChangeListener {
		void onWebsiteChange(String title);

		void onUrlChange(String url);
		//        void onWebsiteChangeBackTop();
	}

	public void setWebsiteChangeListener(WebsiteChangeListener websiteChangeListener) {
		this.mWebsiteChangeListener = websiteChangeListener;

	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
		lp.x = l;
		lp.y = t;
		mProgressBar.setLayoutParams(lp);
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public void setLoadProcessListener(LoadProcessListener processListener) {
		this.processListener = processListener;
	}

	public interface LoadProcessListener {
		void onLoadPageStart(WebView view, String url, Bitmap favicon);
		void onLoadPageFinish(WebView view, String url);
		void onLoadError(WebView view, int errorCode, String description, String failingUrl);
	}

}
