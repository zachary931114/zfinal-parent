package me.zhoubl.zfinal.synthesis.api;


import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.SysRole;
import me.zhoubl.zfinal.synthesis.ex.SysRoleBizEx;

/**
 * <p>
 * 系统角色api
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
public interface SysRoleService extends IService<SysRole> {
    public void create(SysRole entity, String permissionIds, String menuIds) throws SysRoleBizEx;
    public void delete(SysRole entity) throws SysRoleBizEx;
    public void update(SysRole entity, String permissionIds, String menuIds) throws SysRoleBizEx;
    public void updateStatus(SysRole entity, Integer status) throws SysRoleBizEx;
}
