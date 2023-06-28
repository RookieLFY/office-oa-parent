package com.lfy.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lfy.model.system.SysRole;
import com.lfy.vo.system.AssginRoleVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {

    //查询所有角色
    Map<String, Object> findRoleDataByUserId(Long userId);

    void assignRoleDataByUserId(AssginRoleVo assginRoleVo);
}
