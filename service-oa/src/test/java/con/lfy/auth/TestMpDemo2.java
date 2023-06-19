package con.lfy.auth;

import com.lfy.auth.ServiceAuthApplication;
import com.lfy.auth.service.SysRoleService;
import com.lfy.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = ServiceAuthApplication.class)
public class TestMpDemo2 {
    @Resource
    private SysRoleService sysRoleService;


    @Test
    public void getAll(){
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }


}
