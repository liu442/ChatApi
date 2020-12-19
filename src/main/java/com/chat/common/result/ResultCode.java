package com.chat.common.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @author
 * @date 2019/8/15
 * 返回响应结果码集合
 */
public enum ResultCode {
	OK(0,"ok"),
	FAIL(100500,"服务器异常"),
	UNKNOWN_ERROR(1,"未知异常"),
	TOKEN_INVALID(2,"TOKEN失效"),

	SIGN_ERROR(4,"签名错误"),

	USER_NOT_EXIST(10102,"用户不存在"),
	USER_PASS_ERROR(10103,"用户名或密码错误");

	@Setter
	@Getter
	public final int code;
	@Setter
	@Getter
	public final String msg;
	
	ResultCode(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

}
