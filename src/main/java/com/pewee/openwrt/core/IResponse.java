package com.pewee.openwrt.core;
/**
 * 微服务通用的返回信息
 * @author pewee
 *
 */
public interface IResponse {
	
	public String getCode();
	
	public String getMsg();

	public boolean isSuccess();
	
}
