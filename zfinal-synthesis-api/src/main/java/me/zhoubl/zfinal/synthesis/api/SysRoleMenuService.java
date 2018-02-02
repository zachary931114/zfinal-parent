package me.zhoubl.zfinal.synthesis.api;

import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.SysRoleMenu;
import me.zhoubl.zfinal.synthesis.ex.SysRoleMenuBizEx;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubl
 * @since 2017-06-24
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    public void create(SysRoleMenu entity) throws SysRoleMenuBizEx;
    public void deleteByRelevanceId(Long id) throws SysRoleMenuBizEx;
    public List<SysRoleMenu> getAllByRelevanceId(Long id) throws SysRoleMenuBizEx;
}
