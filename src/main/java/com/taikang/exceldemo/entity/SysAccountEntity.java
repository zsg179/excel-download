package com.taikang.exceldemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther :朱树广
 * @Date :2020/7/25
 * @Description :com.taikang.exceldemo.entity
 * @Version :1.0
 */
@Data
@TableName("v_account_sz")
public class SysAccountEntity implements Serializable {
    @TableId
    private Long id;
    /**
     * 投保人姓名
     */
    @TableField("applicantName")
    private String applicantName;
    /**
     * 投保人电话
     */
    @TableField("applicantTel")
    private String applicantTel;
    /**
     * 投保人年龄
     */
    @TableField("applicantAge")
    private String applicantAge;
    /**
     * 投保人性别
     */
    @TableField("applicantSex")
    private String applicantSex;
    /**
     * 所属团购码
     */
    @TableField("ownerCode")
    private String ownerCode;
}
