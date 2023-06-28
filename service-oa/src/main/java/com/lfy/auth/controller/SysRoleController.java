package com.lfy.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lfy.auth.service.SysRoleService;
import com.lfy.auth.service.SysUserRoleService;
import com.lfy.common.result.Result;
import com.lfy.common.result.ResultCodeEnum;
import com.lfy.exception.SelfDefinedException;
import com.lfy.model.system.SysRole;
import com.lfy.vo.system.AssginRoleVo;
import com.lfy.vo.system.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    /*
    * 首先注入service层的接口，使用当层的controller层去调用service层接口去对dao层的mapper进行对数据库的操作
    * */
    @Resource
    private SysRoleService sysRoleService;


//    @GetMapping("/getAll")
//    public List<SysRole> getAll(){
//        //可以直接在当前的controller层调用service，因为MP封装了service层对dao层的mapper对数据库的操作
//        //当前直接调用service层的list方法，查询所有记录，然后返回list集合；
//        List<SysRole> list = sysRoleService.list();
//        //然后直接返回list，最后它会转换成json格式传送给前端
//        return list;
//    }

    //查询所有角色
    @GetMapping("/getAllRole")
    public Result getAll(){
        //可以直接在当前的controller层调用service，因为MP封装了service层对dao层的mapper对数据库的操作
        //当前直接调用service层的list方法，查询所有记录，然后返回list集合；
        List<SysRole> list = sysRoleService.list();
        //然后直接返回list，最后它会转换成json格式传送给前端
        return Result.ok(list);
    }


    //条件分页查询
    //page代表当前页，limit代表每页记录数 参数

    @GetMapping("/pageQueryRole/{page}/{limit}") //因为传参是@PathVariable，所以直接传/pageQueryRole/1/2 这种形式
    //因为{page}以及{limit}都属于路径传值，所以下面参数列表都需要加上@PathVariable,然后还有一个根据条件查询的条件参数，我们是传入对象形式
    public Result pageQueryRole(@PathVariable Long page,
                                @Param("NAME") String name,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo){
        //首先调用sysRoleService的方法去实现

        //1 创建Page对象，传递当前页的参数以及每页记录条参数进去
        Page<SysRole> pageParam = new Page<>(page,limit);
        //2 封装条件，因为条件可能为空，如果判断不为空的话 就给它进行封装
        /*
        * 下面这行代码创建了一个 LambdaQueryWrapper 对象，用于构建数据库查询条件。SysRole 是查询的目标实体类。
        * */
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        //获取查询条件中的查询名称，用于模糊查询
        String roleName = sysRoleQueryVo.getRoleName();
        /*
        * 这行代码使用 like() 方法将模糊查询条件添加到查询包装器中。
        * SysRole::getRoleName 表示查询条件针对 SysRole 实体类的 roleName 属性进行模糊匹配，roleName 是要匹配的值。*/
        if(!StringUtils.isEmpty(roleName)){
            wrapper.like(SysRole::getRoleName,roleName);
        }
        //3 调用方法实现
        Page<SysRole> queryRole = sysRoleService.page(pageParam, wrapper);
        return Result.ok(queryRole);
    }

    //添加角色接口
    //@GetMapping("/insertRole")
    @PostMapping("/insertRole")
    //下面这种通过@RequestBody 即请求体传递参数，可以理解为通过json形式传递，把json数据封装到对象中，最终把对象数据添加到数据库中。
    //在其次，RequestBody是请求体，而Get请求是没有请求提的，所以需要用Post请求来写。
    public Result insertRole(@RequestBody SysRole role){
        boolean is_success = sysRoleService.save(role);
        if(is_success){
           return Result.ok();
        }else {
           return Result.fail();
        }
    }


    //修改角色
    /*
    * 两步走，第一步：根据id查询到角色，第二步根据查询到的角色进行修改*/

    //根据id查询到角色
    @GetMapping("/getRoleById/{id}")
    public Result getRoleById(@PathVariable Long id){
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }

    //修改角色-最终修改
    @PutMapping("/updateRole")
    public Result updateRole(@RequestBody SysRole role){
        boolean is_success = sysRoleService.updateById(role);
        if(is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //删除角色-根据id删除
    @DeleteMapping("/deleteRoleById/{id}")
    public Result deleteRoleById(@PathVariable Long id){
        boolean is_success = sysRoleService.removeById(id);
        if(is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
    //删除角色-批量删除
    //前端可以传入json类型的数组格式：[1，2，3]，后端用@RequestBody List<>来接受
    @DeleteMapping("/batchRemoveRole")
    public Result batchRemoveRole(@RequestBody List<Long> ids){
        boolean is_success = sysRoleService.removeByIds(ids);
        if(is_success){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //查询所有角色 和 当前用户所属角色
    @GetMapping("/toAssign/{id}")
    public Result toAssignByUserId(@PathVariable Long userId){
     Map<String,Object> map = sysRoleService.findRoleDataByUserId(userId);
     return Result.ok(map);
    }

    //为用户分配角色
    @PostMapping("/doAssign")
    public Result doAssignByUserId(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.assignRoleDataByUserId(assginRoleVo);
        return Result.ok();
    }
}
