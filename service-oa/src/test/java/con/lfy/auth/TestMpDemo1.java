package con.lfy.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lfy.auth.ServiceAuthApplication;
import com.lfy.auth.mapper.SysRoleMapper;
import com.lfy.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = ServiceAuthApplication.class)
public class TestMpDemo1 {
    @Resource
    private SysRoleMapper sysRoleMapper;

    //测试查询所有记录
    @Test
    public void getAll(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        System.out.println(sysRoles);
    }
    //测试新增记录
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员");
        int rows = sysRoleMapper.insert(sysRole);
        System.out.println(sysRole);
        System.out.println(sysRole.getId());
    }

    //测试修改记录
    @Test
    public void update(){
        //首先根据id查询出来值
        SysRole role = sysRoleMapper.selectById(2);
        System.out.println(role);
        System.out.println(role.getId());
        //其次修改值
        role.setRoleName("普通管理员");
        //调用方法实现最终修改
        int row = sysRoleMapper.updateById(role);
    }

    //删除记录操作（逻辑删除）
    /**
     * application-dev.yml 加入配置
     * 此为默认值，如果你的默认值和mp默认的一样，则不需要该配置
     * mybatis-plus:
     *   global-config:
     *     db-config:
     *       logic-delete-value: 1
     *       logic-not-delete-value: 0
     */
    @Test
    public void delete(){
        int row = sysRoleMapper.deleteById(10);
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        System.out.println(sysRoles);
    }

    //条件查询
    @Test
    public void testQueryWrapper(){
        //创建QueryWrapper对象，调用方法封装条件
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name","系统管理员");
        //用MP中的方法实现查询操作
        List<SysRole> sysRoles = sysRoleMapper.selectList(wrapper);
        System.out.println(sysRoles);

    }
}
