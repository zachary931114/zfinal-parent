package me.zhoubl.zfinal.synthesis.api;

import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.SysRolePermission;
import me.zhoubl.zfinal.synthesis.ex.SysRolePermissionBizEx;

import java.util.List;


/**
 * <p>
 *  系统角色权限api
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-18
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {
    public void create(SysRolePermission entity) throws SysRolePermissionBizEx;
    public void deleteByRelevanceId(Long id) throws SysRolePermissionBizEx;
    public List<SysRolePermission> getAllByRelevanceId(Long id) throws SysRolePermissionBizEx;
}
