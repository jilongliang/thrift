package com.flong.thrift.charter3;

import org.apache.thrift.async.AsyncMethodCallback;

/**
 * @Description	MethodCallback
 * @ClassName	MethodCallback
 * @Date		2018年2月2日 上午10:47:36
 * @Author		liangjl
 * @Copyright (c) All Rights Reserved, 2018.
 */
@SuppressWarnings("all")
public class MethodCallback implements AsyncMethodCallback {
	Object response = null;

	public Object getResult() {
		// 返回结果值
		return this.response;
	}

	// 处理服务返回的结果值
	@Override
	public void onComplete(Object response) {
		this.response = response;
	}

	// 处理调用服务过程中出现的异常
	@Override
	public void onError(Exception throwable) {

	}

}