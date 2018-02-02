package me.zhoubl.zfinal.synthesis.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.synthesis.api.SysMenuService;
import me.zhoubl.zfinal.synthesis.entity.SysMenu;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import me.zhoubl.zfinal.synthesis.mapper.SysMenuDao;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public List<SysMenu> getAll() {
        return selectList(new EntityWrapper<SysMenu>().eq("status", 1).orderBy("seq,index_val"));
    }

    @Override
    public List<SysMenu> getAllByUser(SysUser entity) {
        return sysMenuDao.getAllByUser(entity);
    }

}
