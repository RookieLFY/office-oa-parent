package com.lfy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lfy.auth.mapper.SysRoleMapper;
import com.lfy.auth.service.SysRoleService;
import com.lfy.auth.service.SysUserRoleService;
import com.lfy.model.system.SysRole;
import com.lfy.model.system.SysUserRole;
import com.lfy.vo.system.AssginRoleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImp extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysUserRoleService sysUserRoleService;

    //查询所有角色，和当前用户所属角色
    @Override
    public Map<String, Object> findRoleDataByUserId(Long userId) {
        //1、查询所有的角色，返回角色数组list
        List<SysRole> allRoleList = baseMapper.selectList(null);
        //2、根据userId查询用户角色关系表，查询userId对应的所有roleId
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId,userId);
        List<SysUserRole> existUserRoleList = sysUserRoleService.list(wrapper);
        //3、根据查询到的角色id，找到对应的角色信息
        List<Long> collectList = existUserRoleList.stream().map(a -> a.getRoleId()).collect(Collectors.toList());
       /* List<Long> list = new ArrayList<>();
        for (SysUserRole sysUserRole:existUserRoleList) {
            Long roleId = sysUserRole.getRoleId();
            list.add(roleId);
        }*/

        //根据角色id，到所有角色数组中去做对比，如果所有角色数组中存在这个角色id，则将它加入到分配角色列表中
        List<SysRole> assignRoleList = new ArrayList<>();
        for (SysRole sysRole:allRoleList) {
            if (collectList.contains(sysRole.getId())){
                assignRoleList.add(sysRole);
            }
        }

        //创建map集合，用以封装刚刚得到的已分配角色数据和所有角色数据
        Map<String,Object> roleMap = new HashMap<>();
        //将已分配的角色列表压入map集合中
        roleMap.put("assignRoleList",assignRoleList);
        //将所有角色列表压入map集合中
        roleMap.put("allRoleList",allRoleList);
        //放入map集合中，在返回
        return roleMap;
    }

    @Override
    public void assignRoleDataByUserId(AssginRoleVo assginRoleVo) {

    }
//    @Resource
//    private SysRoleMapper sysRoleMapper;
}
