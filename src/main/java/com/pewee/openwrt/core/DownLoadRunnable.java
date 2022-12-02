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
	}

}
