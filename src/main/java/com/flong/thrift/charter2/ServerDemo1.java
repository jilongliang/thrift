package com.flong.thrift.charter2;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flong.thrift.charter1.HelloWorldImpl;
import com.flong.thrift.charter1.HelloWorldService;

/**
 * @Description ServerDemo1
 * @ClassName ServerDemo1
 * @Date 2018年1月10日 下午3:16:48
 * @Author liangjl
 * @Copyright (c) All Rights Reserved, 2018.
 */
public class ServerDemo1 {
	static Logger logger = LoggerFactory.getLogger(ServerDemo1.class);

	static int port = 9090;

	public static void main(String[] args) throws TTransportException {

		// 传输层(Transport), 设置监听端口为9090
		TServerSocket serverSocket = new TServerSocket(port);
		
		//协议层
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory(true, true);
		
        
        // 处理层(Processor)
        HelloWorldImpl handler = new HelloWorldImpl();
        HelloWorldService.Processor<HelloWorldImpl> processor = new HelloWorldService.Processor<HelloWorldImpl>(handler);

        //) 服务层(Server)
        TServer server = new TThreadPoolServer( new TThreadPoolServer.Args(serverSocket)
                        .protocolFactory(protocolFactory).processor(processor));
        //  启动监听服务
        server.serve();
        logger.info("服务已经启动，端口为："+port);
		
		
	}

}
