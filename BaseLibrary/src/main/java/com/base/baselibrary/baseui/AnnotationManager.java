package com.base.baselibrary.baseui;

import java.util.HashMap;

/*
 *  @文件名:   AnnotationManager
 *  @创建者:   xuerui
 *  @创建时间:  2018/4/28 17:51
 *  @描述：    运行时注解缓存工具
 */

public class AnnotationManager {
	public static AnnotationManager INSTANCE;
	private HashMap<Class<?>,ContentView> mMap = new HashMap<>();

	private AnnotationManager() {
	}

	static {
		INSTANCE = new AnnotationManager();
	}

	public ContentView getContentViewAnnotation(Class<?> clazz){
		if (mMap.get(clazz)!=null){
			return mMap.get(clazz);
		}
		ContentView annotation = clazz.getAnnotation(ContentView.class);
		if(annotation == null) throw new RuntimeException("Please bind view layout");
		mMap.put(clazz,annotation);
		return annotation;
	}
}
