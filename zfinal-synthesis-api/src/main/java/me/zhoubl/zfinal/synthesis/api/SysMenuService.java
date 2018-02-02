package me.zhoubl.zfinal.synthesis.api;

import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.SysMenu;
import me.zhoubl.zfinal.synthesis.entity.SysUser;

import java.util.List;


/**
 * <p>
 *  系统菜单api
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
public interface SysMenuService extends IService<SysMenu> {
    public List<SysMenu> getAll();
	public List<SysMenu> getAllByUser(SysUser entity);
}
