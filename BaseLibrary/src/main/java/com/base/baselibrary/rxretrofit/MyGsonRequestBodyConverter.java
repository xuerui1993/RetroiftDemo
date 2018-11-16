package com.base.baselibrary.rxretrofit;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/*
 *  @项目名：  OkgoDemo
 *  @包名：    com.base.baselibrary.rxretrofit
 *  @文件名:   MyGsonRequestBodyConverter
 *  @创建者:   xuerui
 *  @创建时间:  2018/6/29 16:35
 *  @描述：    TODO
 */
final class MyGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
	private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	private final Gson gson;
	private final TypeAdapter<T> adapter;

	MyGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
		this.gson = gson;
		this.adapter = adapter;
	}

	@Override public RequestBody convert(T value) throws IOException {
		Buffer buffer = new Buffer();
		Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
		JsonWriter jsonWriter = gson.newJsonWriter(writer);
		adapter.write(jsonWriter, value);
		jsonWriter.close();
		return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
	}
}
