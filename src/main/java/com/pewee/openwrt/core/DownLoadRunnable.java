package com.pewee.openwrt.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
		log.info("开始下载:{}",fileName);
		FileOutputStream outputStream = null;
		try {
			byte[] byteArr = Downloader.getByteArr(url, null);
			if (null != byteArr) {
				outputStream = new FileOutputStream( new File(fileStr) );
				IOUtils.write(byteArr, outputStream);
				log.info("完成下载:{}",fileName);
			}
		} catch (IOException e) {
			log.error("下载文件:" + fileName + "失败!!",e);
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				log.error("系统错误",e);
			}
		}
	}

}
