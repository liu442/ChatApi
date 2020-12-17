package com.steve.common.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 *
 *  返回响应数据
 * @param <T>
 */
@Builder
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class ResultResponse<T> implements Serializable {
	private static final long serialVersionUID = 8591246714369327601L;
	private int code;
	private String msg;
	private T result;

	// 添加无参构造 不然序列化失败  使用了@Builder  必须用 @Tolerate 来添加无参构造 否则报错
 	@Tolerate
	public ResultResponse(){

	}
	/**
	 * 返回成功以及响应数据
	 * @param result
	 * @return
	 */
	public static ResultResponse ok(Object result){
		return ResultResponse.builder()
				.code(ResultCode.OK.getCode())
				.msg(ResultCode.OK.getMsg())
				.result(result)
				.build();
	}

	/**
	 * 返回成功，无响应数据
	 * @return
	 */
	public static ResultResponse ok(){
		return ResultResponse.builder()
				.code(ResultCode.OK.getCode())
				.msg(ResultCode.OK.getMsg())
				.build();
	}


	/**
	 * 返回默认失败原因信息
	 * @return
	 */
	public static ResultResponse fail(){
		return ResultResponse.builder()
				.code(ResultCode.FAIL.getCode())
				.msg(ResultCode.FAIL.getMsg())
				.build();
	}

	/**
	 * 返回自定义失败原因
	 * @param message
	 * @return
	 */
	public static ResultResponse fail(String message) {
		return ResultResponse.builder()
				.code(ResultCode.FAIL.getCode())
				.msg(message)
				.build();
	}

	/**
	 *  返回 ResultCode 枚举中定义的 错误信息
	 * @param resultCode
	 * @return
	 */
	public static ResultResponse fail(ResultCode resultCode){
		return ResultResponse.builder()
				.code(resultCode.getCode())
				.msg(resultCode.getMsg())
				.build();
	}

}
