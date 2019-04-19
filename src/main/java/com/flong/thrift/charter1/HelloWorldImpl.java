package com.flong.thrift.charter1;

import org.apache.thrift.TException;

/**
 * 
 * @Description	HelloWorldImpl实现HelloWorldService.Iface
 * @ClassName	HelloWorldImpl
 * @Date		2018年1月8日 下午5:35:13
 * @Author		liangjl
 * @Copyright (c) All Rights Reserved, 2018.
 */
public class HelloWorldImpl implements HelloWorldService.Iface {

	public HelloWorldImpl() {
	}
	
	@Override
	public String sayHello(String username) throws TException {
		return "Hi," + username + " welcome to thrift world";
	}

}
