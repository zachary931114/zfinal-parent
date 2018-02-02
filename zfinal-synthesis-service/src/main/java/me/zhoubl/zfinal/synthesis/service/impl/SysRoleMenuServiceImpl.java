package me.zhoubl.zfinal.synthesis.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.synthesis.api.SysRoleMenuService;
import me.zhoubl.zfinal.synthesis.entity.SysRoleMenu;
import me.zhoubl.zfinal.synthesis.ex.SysRoleMenuBizEx;
import me.zhoubl.zfinal.synthesis.mapper.SysRoleMenuDao;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-06-24
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public void create(SysRoleMenu entity) throws SysRoleMenuBizEx {
        insert(entity);
    }

    @Override
    public void deleteByRelevanceId(Long id) throws SysRoleMenuBizEx {
        delete(new EntityWrapper<SysRoleMenu>().eq("sys_role_id", id));
    }

    @Override
    public List<SysRoleMenu> getAllByRelevanceId(Long id) throws SysRoleMenuBizEx {
        return selectList(new EntityWrapper<SysRoleMenu>().eq("sys_role_id", id));
    }
}
