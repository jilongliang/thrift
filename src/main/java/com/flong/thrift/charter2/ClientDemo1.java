package com.flong.thrift.charter2;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flong.thrift.charter1.HelloWorldService;

/**
 * @Description ServerDemo1
 * @ClassName ServerDemo1
 * @Date 2018年1月10日 下午3:16:48
 * @Author liangjl
 * @Copyright (c) All Rights Reserved, 2018.
 */
public class ClientDemo1 {
	static Logger logger = LoggerFactory.getLogger(ClientDemo1.class);

	static int port = 9090;

	public static void main(String[] args) throws TException {
		// *) 传输层
		TTransport transport = new TSocket("localhost", port);
		transport.open();
		// *) 协议层, 与服务端对应
		TProtocol protocol = new TBinaryProtocol(transport);
		// *) 创建RPC客户端
		HelloWorldService.Client client = new HelloWorldService.Client(protocol);
		// *) 调用服务
		System.out.println(client.sayHello("flong"));
		// *) 关闭句柄
		transport.close();

	}

}
