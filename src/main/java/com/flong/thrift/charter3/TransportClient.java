package com.flong.thrift.charter3;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;

/**
 * @Description 文件传输客户端
 * https://www.ibm.com/developerworks/cn/java/j-lo-apachethrift/index.html
 * 
 * @ClassName TransportClient
 * @Date 2018年1月11日 下午4:25:53
 * @Author liangjl
 * @Copyright (c) All Rights Reserved, 2018.
 */
public class TransportClient {

	/**
	 * 类内测试 main 函数
	 */
	public static void main(String[] args) {
		String filePath = "D:/c.jpg";
		//blockingClient(filePath);
		nonBlockingClient(filePath);
		
	}

	/**
	 * 非阻塞
	 */
	private static void nonBlockingClient(String filePath) {
		FileEntity fileData = fileObject(filePath);
		// 构造Thrift客户端，发起请求
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager(); 
	        TNonblockingTransport transport = new TNonblockingSocket("localhost", 8081); 
	        TProtocolFactory protocol = new TBinaryProtocol.Factory(); 
	        
	        FileService.AsyncClient asyncClient = new FileService.AsyncClient(protocol,  clientManager, transport); 
	      
	        MethodCallback	callBack = new MethodCallback();  
	       
	        asyncClient.uploadFile(fileData, callBack);
	        
	        Object res = callBack.getResult(); //等待
            while (res == null) { 
               res = callBack.getResult(); 
            } 
            System.out.println(((FileService.AsyncClient.uploadFile_call) res).getResult()); 
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	
	/**
	 * TSocket属于阻塞
	 */
	private static void blockingClient(String filePath) {
		FileEntity fileData = fileObject(filePath);
		// 构造Thrift客户端，发起请求
		try {
			//创建Socket
			TSocket socket = new TSocket("localhost", 8081);
			socket.setSocketTimeout(60 * 1000);
			TFramedTransport framedTransport = new TFramedTransport(socket);
			//打开TFramedTransport
			framedTransport.open();
			//插件协议
			TBinaryProtocol binaryProtocol = new TBinaryProtocol(framedTransport);
			//调用客户端进行拷贝文件
			FileService.Client client = new FileService.Client(binaryProtocol);
			client.uploadFile(fileData);
		} catch (Exception x) {
			x.printStackTrace();
		}
	}



	private static FileEntity fileObject(String filePath) {
		// 构造文件数据
		byte[] bytes = toByteArray(filePath);
		FileEntity fileData = new FileEntity();
		fileData.name = filePath;
		fileData.buff = ByteBuffer.wrap(bytes);
		return fileData;
	}

	/**
	 * 文件转化为字节数组
	 */
	private static byte[] toByteArray(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;

	}
}
