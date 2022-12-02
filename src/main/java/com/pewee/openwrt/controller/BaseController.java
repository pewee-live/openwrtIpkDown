package com.pewee.openwrt.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pewee.openwrt.core.RespEntity;
import com.pewee.openwrt.core.ServiceException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class BaseController {
	
	@ExceptionHandler
	public RespEntity<String> handException(HttpServletRequest request ,Exception e){
		log.error(e.getMessage(),e);
		RespEntity<String> result = new RespEntity<String>();
		result.applySuccess(false);
		if(e instanceof
				ServiceException) {
			result.applyRespMsg(((ServiceException)e).getMsg());
			result.applyRespCode(((ServiceException)e).getCode());
			result.setData(((ServiceException)e).getMessage());
		} 
		else if (e instanceof IllegalArgumentException) {
			result.applyRespMsg(((IllegalArgumentException)e).getMessage());
			result.applyRespCode("-1000001");
			result.setData(((IllegalArgumentException)e).getMessage());
		} else if (e instanceof MethodArgumentNotValidException) {
			result.applyRespMsg(((MethodArgumentNotValidException)e).getAllErrors().get(0).getDefaultMessage());
			result.applyRespCode("-1000001");
			result.setData(((MethodArgumentNotValidException)e).getMessage());
		} else if (e instanceof ConstraintViolationException) {
			result.applyRespMsg(((ConstraintViolationException)e).getConstraintViolations().iterator().next().getMessage());
			result.applyRespCode("-1000001");
			result.setData(((ConstraintViolationException)e).getMessage());
		}
		else {
			result.applyRespCode("-1000000");
			result.applyRespMsg("系统异常");
			result.setData(e.getClass().getCanonicalName()  + ":" + e.getMessage());
		}
		return result;
	}
	
}
