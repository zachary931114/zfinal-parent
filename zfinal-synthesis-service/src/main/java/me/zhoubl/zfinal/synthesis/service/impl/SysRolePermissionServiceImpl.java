package me.zhoubl.zfinal.synthesis.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.synthesis.api.SysRolePermissionService;
import me.zhoubl.zfinal.synthesis.entity.SysRolePermission;
import me.zhoubl.zfinal.synthesis.ex.SysRolePermissionBizEx;
import me.zhoubl.zfinal.synthesis.mapper.SysRolePermissionDao;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-18
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionDao, SysRolePermission> implements SysRolePermissionService {

    @Override
    public void create(SysRolePermission entity) throws SysRolePermissionBizEx {
        insert(entity);
    }

    @Override
    public void deleteByRelevanceId(Long id) throws SysRolePermissionBizEx {
        delete(new EntityWrapper<SysRolePermission>().eq("sys_role_id", id));
    }

    @Override
    public List<SysRolePermission> getAllByRelevanceId(Long id) throws SysRolePermissionBizEx {
        return selectList(new EntityWrapper<SysRolePermission>().eq("sys_role_id", id));
    }
}
