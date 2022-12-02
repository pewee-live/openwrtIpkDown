package com.pewee.openwrt.core;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 返回对象
 * @author pewee
 *
 */
public class RespEntity<T> implements IResponse,Serializable{


	private static final long serialVersionUID = -1243557622261868440L;
	private String code;
	
	private String msg;

	private boolean success;
	
	private T data;
	
	public RespEntity() {
		super();
	}

	public RespEntity(IResponse resp, T data) {
		this.code = resp.getCode();
		this.msg = resp.getMsg();
		this.success = resp.isSuccess();
		this.data = data;
	}

	public RespEntity(IResponse resp) {
		this.code = resp.getCode();
		this.msg = resp.getMsg();
		this.success = resp.isSuccess();
	}
	
	public RespEntity<T> applyRespCodeMsg(IResponse resp) {
		this.code = resp.getCode();
		this.msg = resp.getMsg();
		this.success = resp.isSuccess();
		return this;
	}
	
	public RespEntity<T> applyRespCode(String code) {
		this.code = code;
		this.success = !code.startsWith("-");
		return this;
	}
	
	public RespEntity<T> applyRespMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public RespEntity<T> applySuccess(boolean success) {
		this.success = success;
		return this;
	}
	
	public RespEntity<T> applyData(T data) {
		this.data = data;
		return this;
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}
	
	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}

	@Override
	public boolean isSuccess() {
		return this.success;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
