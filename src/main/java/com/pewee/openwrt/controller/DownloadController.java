package com.pewee.openwrt.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pewee.openwrt.core.CommonTask;
import com.pewee.openwrt.core.DownLoadRunnable;
import com.pewee.openwrt.core.Downloader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DownloadController {
	
	public static List<String> container = new ArrayList<>();
	
	static {
		container.add("");
	}
	
	/**
	 * 下载固件 url=  https://openwrt.cc/releases/targets/bcm27xx/bcm2711/
	 * @param url
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/downloadAllFirmWare")
	public String downloadAllFirmWare(@RequestParam String url ) throws IOException {
		log.info("开始下载:{}",url);
		if (!url.endsWith("/")) {
			return "url必须以/结尾";
		}
		String archType = getArchType(url);
		String dirStr = "Download/FirmWare/" + archType + "/";
		File dir = new File(dirStr);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String string = Downloader.get(url, null);
		String[] lines = string.split("\n");
		int index = 0;
		String temp = "";
		for (int i = 0; i <lines.length ;i++ ) {
			if (lines[i].contains("#file") && index == i -1 ) {
				String fileName =  temp.substring(temp.indexOf("\"") + 1,temp.lastIndexOf("\"")) ;
				String filestr = dirStr + fileName;
				String fileUrl = url + fileName;
				downloadFirmWare(fileName,filestr,fileUrl);
			}
			
			if (lines[i].contains("href")) {
				index = i;
				temp = lines[i];
			}
		}
		return "OK!";
	}
	
	/**
	 * 下载固件
	 * @param fileName
	 * @param filestr
	 * @param fileUrl
	 */
	private void downloadFirmWare(String fileName, String filestr, String fileUrl) {
		CommonTask.executor.execute(new DownLoadRunnable(filestr, fileName, fileUrl));
	}


	//下载软件包 https://openwrt.cc/snapshots/packages/aarch64_cortex-a72/
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	@GetMapping("/downloadAllSoft")
	public String downloadUrl(@RequestParam String url ) throws IOException {
		log.info("开始下载:{}",url);
		if (!url.endsWith("/")) {
			return "url必须以/结尾";
		}
		String archType = getArchType(url);
		String dirStr = "Download/" + archType + "/";
		File dir = new File(dirStr);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String string = Downloader.get(url, null);
		String[] lines = string.split("\n");
		int index = 0;
		String temp = "";
		for (int i = 0; i <lines.length ;i++ ) {
			if (lines[i].contains("#folder") && index == i -1 ) {
				String folderName =  temp.substring(temp.indexOf("\"") + 1,temp.lastIndexOf("\"")) ;
				String folderstr = dirStr + folderName;
				File folder = new File(folderstr);
				if ( !folder.exists()) {
					folder.mkdirs();
				}
				String ipkurl = url + folderName;
				downloadIpk(ipkurl,folderstr);
				
			}
			if (lines[i].contains("href")) {
				index = i;
				temp = lines[i];
			}
		}
		return "OK!";
	}
	
	/**
	 * 下载ipk
	 * @param ipkurl
	 * @param folderstr
	 * @throws IOException 
	 */
	private void downloadIpk(String ipkurl, String folderstr) throws IOException {
		String string = Downloader.get(ipkurl, null);
		String[] lines = string.split("\n");
		for (int i = 0; i <lines.length ;i++ ) {
			String tmp = lines[i];
			if (tmp.contains("href") && (
					tmp.contains("ipk") ||
					tmp.contains("Packages") 
					) &&  !tmp.contains("svg")) {
				String fileName =  tmp.substring(tmp.indexOf("\"") + 1,tmp.lastIndexOf("\"")) ;
				String fileStr =folderstr +  fileName;
				CommonTask.executor.execute(new DownLoadRunnable(fileStr, fileName, ipkurl + fileName));
				
			}
		}
	}

	/**
	 * 获取架构类型
	 * https://openwrt.cc/snapshots/packages/aarch64_cortex-a72 ---> aarch64_cortex-a72
	 * https://openwrt.cc/releases/targets/bcm27xx/bcm2711/  ---> bcm2711
	 * @param url
	 * @return
	 */
	private  String getArchType(String url) {
		String substring = url.substring(0, url.length() - 1);
		return substring.substring(substring.lastIndexOf("/") + 1,substring.length());
	}
	
}
