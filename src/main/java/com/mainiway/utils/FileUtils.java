package com.mainiway.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* @author  yanfeng.shen 
* @date 创建时间：2017年3月16日 下午5:59:56 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class FileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * 使用Java.nio ByteBuffer字节将一个文件输出至另一文件
	 * @param filePath
	 */
	public static String readFileByBybeBuffer(File file,String upNamePath) throws Exception{
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			// 获取源文件和目标文件的输入输出流
			in = new FileInputStream(file);
			File file1 = new File(upNamePath);
			if(!file1.getParentFile().exists()){
				file1.getParentFile().mkdirs();
			}
			file1.createNewFile();
			out = new FileOutputStream(file1);
			// 获取输入输出通道
			FileChannel fcIn = in.getChannel();
			FileChannel fcOut = out.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024*4);
			while (true) {
				// clear方法重设缓冲区，使它可以接受读入的数据
				buffer.clear();
				// 从输入通道中将数据读到缓冲区
				int r = fcIn.read(buffer);
				if (r == -1) {
					fcIn.close();
					break;
				}
				// flip方法让缓冲区可以将新读入的数据写入另一个通道
				buffer.flip();
				fcOut.write(buffer);
			}
		} catch (Exception e) {
			try {
				if(in!=null){
					in.close();
				}
				if(out!=null){
					out.close();
				}
			} catch (IOException e1) {
				logger.error("readFileByBybeBuffer :"+e1.getMessage(),e1);
				throw new Exception(e.getMessage());
			}
		} finally {
			try {
				if(in!=null){
					in.close();
				}
				if(out!=null){
					out.close();
				}
			} catch (IOException e1) {
				logger.error("readFileByBybeBuffer :"+e1.getMessage(),e1);
				throw new Exception(e1.getMessage());
			}
		}
		return upNamePath;
	}
}
