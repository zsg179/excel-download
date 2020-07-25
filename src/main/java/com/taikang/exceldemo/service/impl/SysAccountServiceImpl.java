package com.taikang.exceldemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taikang.exceldemo.dao.SysAccountDao;
import com.taikang.exceldemo.entity.SysAccountEntity;
import com.taikang.exceldemo.service.SysAccountService;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;
import java.util.List;

/**
 * @Auther :朱树广
 * @Date :2020/7/25
 * @Description :com.taikang.exceldemo.service.impl
 * @Version :1.0
 */
@Service("sysAccountService")
public class SysAccountServiceImpl extends ServiceImpl<SysAccountDao, SysAccountEntity> implements SysAccountService {
    @Override
    public List<SysAccountEntity> download(String key) {
        QueryWrapper<SysAccountEntity> wrapper = new QueryWrapper<SysAccountEntity>().eq(StringUtils.isNotBlank(key), "ownerCode", key);
        return this.baseMapper.selectList(wrapper);
    }
}
