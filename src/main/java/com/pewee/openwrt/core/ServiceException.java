package com.pewee.openwrt.core;


/**
 * 通用的服务异常
 * @author pewee
 *
 */
public class ServiceException extends RuntimeException implements IResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5933752584125462129L;

	private String retutnCode;
	
	private String returnMsg;

	private final boolean returnSucess = false;
	
	public ServiceException(IResponse resp){	
		super(resp.getMsg(),new RuntimeException());
        this.retutnCode = resp.getCode();
        this.returnMsg = resp.getMsg();
    }
	
	public ServiceException(String returnCode, String returnMsg){
		super(returnMsg,new RuntimeException());
        this.retutnCode = returnCode;
        this.returnMsg = returnMsg;
    }
	
	public ServiceException(IResponse resp, Exception e) {
		super(e.getMessage(), e);
        this.retutnCode = resp.getCode();
        this.returnMsg = resp.getMsg();
    }
	
	public ServiceException(String returnCode, String returnMsg, Exception e) {
		super(e.getMessage(), e);
        this.retutnCode = returnCode;
        this.returnMsg = returnMsg;
    }


	@Override
	public String getCode() {
		return this.retutnCode;
	}

	@Override
	public String getMsg() {
		return this.returnMsg;
	}

	@Override
	public boolean isSuccess() {
		return this.returnSucess;
	}

}
