package com.base.baselibrary.baseui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.inputmethod.InputMethodManager;

import com.base.baselibrary.utils.ToastUtil;

import butterknife.ButterKnife;

/*
 *  @文件名:   BasicActivity
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/12 9:25
 *  @描述：    基类activity,使用注解去注入布局
 */
public abstract class BasicActivity extends RxAppActivity{

	private InputMethodManager mInputMethodManager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		ContentView contentView = AnnotationManager.INSTANCE.getContentViewAnnotation(getClass());
		setContentView(getLayoutRes());
		ButterKnife.bind(this);
		initContent();
	}

	protected abstract int getLayoutRes();


	protected void initContent() {

	}

	protected void startActivity(Class activity, boolean isFinish) {
		Intent intent = new Intent(this, activity);
		startActivity(intent);
		if (isFinish) {
			finish();
		}
	}

	protected void startActivity(Class activity) {
		startActivity(activity, true);
	}

	protected void showProgressDialog(String message) {
//		if (mProgressDialog == null) {
//			mProgressDialog = new ProgressDialog(this);
//		}
//		mProgressDialog.setCanceledOnTouchOutside(false);
//		mProgressDialog.setMessage(message);
//		mProgressDialog.show();
	}

	public void hideKeyboard() {
		//懒加载
		if (mInputMethodManager == null) {
			mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		}
		//getCurrentFocus获取当前有焦点的view
		//隐藏软键盘
		if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
			mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
	}

	public void toast(String message) {
		ToastUtil.showToast(message);
	}
}
