package com.lfy.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfy.auth.mapper.SysRoleMapper;
import com.lfy.auth.service.SysRoleService;
import com.lfy.model.system.SysRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysRoleServiceImp extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
//    @Resource
//    private SysRoleMapper sysRoleMapper;
}
