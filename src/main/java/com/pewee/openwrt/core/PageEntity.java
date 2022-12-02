package com.pewee.openwrt.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author by GongRan
 * @Classname PageEntity
 * @Description 分页返回的实体
 * @Date 2022/9/16 14:08
 */
@Getter
@Setter
@ToString
public class PageEntity<T> implements Serializable {

    private static final long serialVersionUID = 3615414481732420338L;

    /**
     * 满足查询条件的数据总量
     */
    private Long count;

    /**
     * 分页结果集
     */
    private List<T> list;
}
