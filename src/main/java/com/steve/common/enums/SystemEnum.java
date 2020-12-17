package com.steve.common.enums;

/**
 * 
 * @Description: 系统枚举
 */
public enum SystemEnum {
	LIKE(1, "点赞"),
	NOLIKE(2, "取消点赞"),

	SYS_LIKE(1, "点赞"),
	SYS_COMMENT(2, "评论"),
	SYS_REPLY(3, "回复");

	public final Integer key;
	public final String value;

	SystemEnum(Integer key, String value){
		this.key = key;
		this.value = value;
	}
	
	public Integer getKey() {
		return key;
	}  
}
