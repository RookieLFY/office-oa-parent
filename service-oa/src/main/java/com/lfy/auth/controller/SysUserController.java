package com.lfy.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfy.auth.service.SysUserService;
import com.lfy.common.result.Result;
import com.lfy.model.system.SysUser;
import com.lfy.vo.system.SysUserQueryVo;
import jdk.nashorn.internal.ir.LabelNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lfy
 * @since 2023-06-26
 */
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    //首先注入Service层
    @Resource
    private SysUserService sysUserService;

    //用户条件分页查询
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        SysUserQueryVo sysUserQueryVo){
        //创建Page对象
        Page<SysUser> pageParam = new Page<>(page,limit);
        //封装条件，进行判断，条件值不为空
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        //获取条件值
        String username = sysUserQueryVo.getKeyword();
        String createTimeBegin = sysUserQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysUserQueryVo.getCreateTimeEnd();

        //如果字段不为空，则进行like模糊匹配查询
        if(!StringUtils.isEmpty(username)){
            wrapper.like(SysUser::getUsername,username);
        }
        //ge:大于等于
        if(!StringUtils.isEmpty(createTimeBegin)){
            wrapper.ge(SysUser::getCreateTime,createTimeBegin);
        }
        //le：小于等于
        if(!StringUtils.isEmpty(createTimeEnd)){
            wrapper.le(SysUser::getCreateTime,createTimeEnd);
        }
        IPage<SysUser> userPage = sysUserService.page(pageParam, wrapper);
        return Result.ok(userPage);
    }







}

