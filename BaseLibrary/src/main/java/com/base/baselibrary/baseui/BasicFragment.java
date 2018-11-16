package com.base.baselibrary.baseui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;

public abstract class BasicFragment extends Fragment {

	private ProgressDialog mProgressDialog;
	private View mRoot;
	public Context mContext;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mRoot == null) {
			ContentView contentView = AnnotationManager.INSTANCE.getContentViewAnnotation(getClass());
			mRoot = inflater.inflate(contentView.value(), null);
			ButterKnife.bind(this, mRoot);
			mContext = getContext();
			initContent();
		}
		return mRoot;
	}

	public View getRoot() {
		return mRoot;
	}

	protected void initContent() {

	}

	protected abstract int getLayoutResId();

	protected void showProgressDialog(String message) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(getContext());
		}
		mProgressDialog.setMessage(message);
		mProgressDialog.show();
	}

	protected void hideProgressDialog() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}
	}

	public void toast(String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}

	protected void startActivity(Class activity) {
		startActivity(activity, true);
	}

	protected void startActivity(Class activity, boolean isFinish) {
		Intent intent = new Intent(getContext(), activity);
		startActivity(intent);
		if (isFinish) {
			getActivity().finish();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
