package me.zhoubl.zfinal.synthesis.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.synthesis.api.SysUserRoleService;
import me.zhoubl.zfinal.synthesis.entity.SysUserRole;
import me.zhoubl.zfinal.synthesis.ex.SysUserRoleBizEx;
import me.zhoubl.zfinal.synthesis.mapper.SysUserRoleDao;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Override
    public void create(SysUserRole entity) throws SysUserRoleBizEx {
        insert(entity);
    }

    @Override
    public void deleteByRelevanceId(Long id) throws SysUserRoleBizEx {
        delete(new EntityWrapper<SysUserRole>().eq("sys_user_id", id));
    }

    @Override
    public List<SysUserRole> getAllByRelevanceId(Long id) throws SysUserRoleBizEx {
        return selectList(new EntityWrapper<SysUserRole>().eq("sys_user_id", id));
    }
}
