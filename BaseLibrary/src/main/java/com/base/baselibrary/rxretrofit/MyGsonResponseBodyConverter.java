package com.base.baselibrary.rxretrofit;

import com.base.baselibrary.utils.TLog;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.Reader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/*
 *  @文件名:   MyGsonResponseBodyConverter
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/29 16:31
 *  @描述：    TODO
 */
final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
	private final Gson gson;
	private final TypeAdapter<T> adapter;


	MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
		this.gson = gson;
		this.adapter = adapter;
	}

	@Override
	public T convert(ResponseBody value) throws IOException {
//		String response = value.string();
//		BaseResult baseResult = gson.fromJson(response, BaseResult.class);
//		//不是正确的状态码，就统一抛出BaseException将该异常交给onError()去处理了。
//		if (baseResult.getCode() != Constant.SUCCESS) {
//			value.close();
//			throw new BaseException(baseResult.getCode(), baseResult.getMsg());
//		}

		JsonReader jsonReader = null;
		Reader reader = value.charStream();
		jsonReader = gson.newJsonReader(reader);
		TLog.error("reader = " + reader + ",jsonReader = " + jsonReader) ;
		T result = null;
		try {
			result = adapter.read(jsonReader);
			if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
				throw new JsonIOException("JSON document was not fully consumed.");
			}
			return result;
		} catch (Exception e) {
			TLog.error("e " + e);
			e.printStackTrace();
			return result;
		} finally {
			value.close();
		}
	}

}
