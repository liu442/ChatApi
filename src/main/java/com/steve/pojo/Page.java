package com.steve.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;

/**
 * @author Steve
 * @date 2020/2/3
 */
@Setter
@Getter
public class Page {
    @Transient
    private Integer page;
    @Transient
    private Integer pageSize;
    @Transient
    private String field;
    @Transient
    private String sort;
}
