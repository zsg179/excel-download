package com.taikang.exceldemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taikang.exceldemo.entity.SysAccountEntity;

import java.util.List;

/**
 * @Auther :朱树广
 * @Date :2020/7/25
 * @Description :com.taikang.exceldemo.service
 * @Version :1.0
 */
public interface SysAccountService extends IService<SysAccountEntity> {
    List<SysAccountEntity> download(String key);
}
