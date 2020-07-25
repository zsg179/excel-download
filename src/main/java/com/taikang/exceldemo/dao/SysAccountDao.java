package com.taikang.exceldemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taikang.exceldemo.entity.SysAccountEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther :朱树广
 * @Date :2020/7/25
 * @Description :com.taikang.exceldemo.dao
 * @Version :1.0
 */
@Mapper
public interface SysAccountDao extends BaseMapper<SysAccountEntity> {
}
