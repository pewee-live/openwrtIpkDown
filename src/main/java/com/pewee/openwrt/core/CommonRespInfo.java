package com.pewee.openwrt.core;

/**
 * 返回
 * @author pewee
 *
 */
public enum CommonRespInfo implements IResponse {
	SYS_ERROR("-100000","系统错误",false),
	TOKEN_UN_AUTH("-100001","Token未授权",false),
	NOT_LEGAL_PARAM("-100002","参数错误",false),
	ILLEGAL_TOKEN("-100003","登录凭证无效",false),
	USER_NOT_EXITS("-100004","用户不存在",false),
	EXPIRE_TOKEN("-100005","登录凭证过期",false),
	USER_BANNED("-100006","用户已被禁用",false),
	NEED_CAPTCHA("-100007","登录需要验证码",false),
	CAPTCHA_EXPIRE("-100008","验证码失效",false),
	CAPTCHA_ERROR("-100009","验证码错误次数:",false),
	MAX_CAPTCHA_ERROR_TIME("-100010","验证码错误次数超限,请等待%s秒后再尝试!",false),
	MAX_IP_ERROR_TIME("-100011","该ip登录错误次数超限,请等待%s秒后再尝试!",false),
	USER_NOT_ENABLE("-100012","该用户已禁用",false),
	PASSWORD_ERROR("-100013","密码错误次数:",false),
	MAX_PASSWORD_ERROR_TIME("-100014","密码错误次数超限,请等待%s秒后再尝试!",false),
	SIGN_ERROR("-100015","签名错误",false),
	USER_UN_AUTH("-100016","用户未授权此操作",false),
	TIME_OUT("-100017","时间戳已过期",false),
	ANTI_REPLAY("-100018","该请求为重放攻击,已拒绝",false),
	HOST_NOT_ALLOWED("-100019","服务器禁止访问",false),
	REQ_LIMITED("-100020","请求被限流",false),
	IDEMPOTENT_PROTECT("-100021","幂等保护",false),
	VIOLATION_INTERFACE_SETTING("-100022","违反接口设定",false),
	OPERATE_INVALID("-100023","操作无效",false),
	FORMAT_ERROR("-100024","格式错误",false),
	PARSE_ERROR("-100025","解析错误",false),
	PLEASE_INSERT_RIGHT_INFO("-200001","请输入正确信息",false),
	IMPORT_REPEAT_DATA("-100026","导入数据已存在",false),
	INVALID_DATA("-100027","存在非法数据",false),
	SUCCESS("000000","成功",true),
	PARTIAL_SUCCESS("000001","部分成功",true),
	VIOLATION_DICT_VALUE_SETTING("-100028","违反字典设定",false),
	;
	
	CommonRespInfo(String code, String msg, boolean success) {
		this.code = code;
		this.msg = msg;
		this.success = success;
	}
	
	private String code;
	
	private String msg;

	private boolean success;
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public boolean isSuccess() {
		return success;
	}


}
