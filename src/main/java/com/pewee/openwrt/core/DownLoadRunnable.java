package com.pewee.openwrt.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 下载线程
 * @author pewee
 *
 */
@Data
@Slf4j
public class DownLoadRunnable implements Runnable{
	
	
	private String fileStr;
	
	private String fileName;
	
	private String url;
	
	public DownLoadRunnable (String fileStr,String fileName,String url) {
		this.fileStr = fileStr;
		this.fileName = fileName;
		this.url = url;
	}
	
	@Override
	public void run() {
		
		/**
		FileOutputStream outputStream = null;
		try {
			byte[] byteArr = Downloader.getByteArr(url, null);
			if (null != byteArr) {
				outputStream = new FileOutputStream( new File(fileStr) );
				IOUtils.write(byteArr, outputStream);
			} else {
				log.error("文件:\n" + fileName + "\n 下载地址:" + url + "  \n 没有下载成功,请手动下载!!");
			}
		} catch (IOException e) {
			log.error("下载文件:" + fileName + "失败!!",e);
		} finally {
			try {
				if (null != outputStream) {
					outputStream.close();
				}
			} catch (IOException e) {
				log.error("系统错误",e);
			}
		}
		**/
		byte[] arr = new byte[1024];
		FileOutputStream outputStream = null;
		InputStream inputStream = Downloader.getInputStream(url, null);
		try {
			outputStream = new FileOutputStream( new File(fileStr) );
			while (-1 != inputStream.read(arr)) {
				IOUtils.write(arr, outputStream);
			}
			log.info("下载文件:" + fileName + "完成!!");
		} catch (IOException e) {
			log.error("下载文件:" + fileName + "失败!!",e);
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != outputStream) {
					outputStream.close();
				}
			} catch (IOException e) {
				log.error("系统错误",e);
			}
		}
		
	}
}
