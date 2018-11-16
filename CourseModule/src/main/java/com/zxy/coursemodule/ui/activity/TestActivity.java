package com.zxy.coursemodule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zxy.coursemodule.R;

/*
 *  @项目名：  zxy-Android-client
 *  @包名：    com.zxy.coursemodule.ui.activity
 *  @文件名:   TestActivity
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/26 16:58
 *  @描述：    TODO
 */

@Route(path = "/test/activity")
public class TestActivity extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ARouter.getInstance().build("/app/main").navigation();
			}
		});
	}


}
