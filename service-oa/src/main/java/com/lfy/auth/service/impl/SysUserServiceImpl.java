package com.lfy.auth.service.impl;

import com.lfy.auth.mapper.SysUserMapper;
import com.lfy.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfy.exception.SelfDefinedException;
import com.lfy.model.system.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lfy
 * @since 2023-06-26
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public void updateStatus(Long id, Integer status) {
        //首先根据userId查询出来对应的User对象
        SysUser sysUser = baseMapper.selectById(id);
        //其次在user对象中修改ststus值
            //这里还可以判断一下传入的status值是否为0和1，若不是则返回异常处理
        if(StringUtils.isEmpty(status)|| (status!=0 && status!=1)){
            throw new SelfDefinedException(701,"输入的参数必须是0或1");
        }else {
            sysUser.setStatus(status);
        }
        //最后将修改后的对象返回
        baseMapper.updateById(sysUser);
    }
}
