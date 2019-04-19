package com.flong.thrift.charter3;

import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import org.apache.thrift.TException;

public class FileServiceImpl implements FileService.Iface {
	@Override
	public boolean uploadFile(FileEntity filedata) throws TException {
		System.out.println("uploadFile function has been called.");

		// 写到文件
		String filePath = "D:/2.jpg";
		System.out.println("======="+filedata.getName());
		try {
			java.io.File file = new java.io.File(filePath);
			FileOutputStream fos = new FileOutputStream(file);
			FileChannel channel = fos.getChannel();
			channel.write(filedata.buff);
			channel.close();
			fos.flush();
			fos.close();
		} catch (Exception x) {
			x.printStackTrace();
			return false;
		}
		return true;
	}
}
