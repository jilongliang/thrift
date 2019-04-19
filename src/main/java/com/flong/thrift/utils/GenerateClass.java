package com.flong.thrift.utils;

import java.io.IOException;

public class GenerateClass {
	public static void main(String[] args) {
		String thrifFile = "ThriftService.thrift";//
		//String strCmd = "./src/main/java/thrift-0.9.3.exe -r -gen java ./src/main/java/"+thrifFile;
		String strCmd = "./src/main/java/thrift-0.9.3.exe -r -gen php ./src/main/java/"+thrifFile;
		
		cmd2(strCmd);
	}
	
	protected static void cmd2(String thrifFile) {
		//thrift-0.9.3.exe -r -gen java D:\work\workspace\thriftworkspace\demo1\DemoHello.thrift
		
		try {
			Runtime.getRuntime().exec(thrifFile);
			System.out.println("执行完===");
		} catch (IOException e) {
			e.printStackTrace();
		} // 通过执行cmd命令调用protoc.exe程序
	}
}