package me.zhoubl.zfinal.synthesis.api;


import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.SysPermission;
import me.zhoubl.zfinal.synthesis.ex.SysPermissionBizEx;

/**
 * <p>
 * 系统权限api
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
public interface SysPermissionService extends IService<SysPermission> {
    public void create(SysPermission entity) throws SysPermissionBizEx;
    public void delete(SysPermission entity) throws SysPermissionBizEx;
    public void update(SysPermission entity) throws SysPermissionBizEx;
    public void updateStatus(SysPermission entity, Integer status) throws SysPermissionBizEx;
    public int getCountByCode(String code) throws SysPermissionBizEx;
}
