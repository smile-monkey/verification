package com.technology.entity;

import lombok.Data;

import java.util.Date;
@Data
public class BizOrder {
    private Integer id;
    private String orderNo;
    private Date createTime;
    private Date updateTime;
}
