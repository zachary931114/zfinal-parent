package me.zhoubl.zfinal.synthesis.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.common.utils.UtilBean;
import me.zhoubl.zfinal.synthesis.api.*;
import me.zhoubl.zfinal.synthesis.entity.*;
import me.zhoubl.zfinal.synthesis.ex.SysRoleBizEx;
import me.zhoubl.zfinal.synthesis.mapper.SysRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public void create(SysRole entity, String permissionIds, String menuIds) throws SysRoleBizEx {
        if (selectCount(new EntityWrapper<SysRole>().eq("code", entity.getCode())) > 0) {
            throw new SysRoleBizEx(SysRoleBizEx.BIZ_ERROR_CODE, "编码已存在");
        }
        insert(entity);
        updatePermissionItems(entity, permissionIds);
        updateMenuItems(entity, menuIds);
    }

    @Override
    public void delete(SysRole entity) throws SysRoleBizEx {
        deleteById(entity.getId());
    }

    @Override
    public void update(SysRole entity, String permissionIds, String menuIds) throws SysRoleBizEx {
        if (selectCount(new EntityWrapper<SysRole>().eq("code", entity.getCode()).ne("id", entity.getId())) > 0) {
            throw new SysRoleBizEx(SysRoleBizEx.BIZ_ERROR_CODE, "编码已存在");
        }
        SysRole e = selectById(entity.getId());
        UtilBean.copyPropertiesIgnoreNull(entity, e);
        updateById(e);
        updatePermissionItems(entity, permissionIds);
        updateMenuItems(entity, menuIds);
    }

    private void updatePermissionItems(SysRole entity, String idsStr) {
        if (!Strings.isNullOrEmpty(idsStr)) {
            sysRolePermissionService.deleteByRelevanceId(entity.getId());
            String[] ids = idsStr.split(",");
            for (String id : ids) {
                if (!Strings.isNullOrEmpty(id)) {
                    SysRolePermission target = new SysRolePermission();
                    target.setSysRoleId(entity.getId());
                    target.setSysPermissionId(Long.valueOf(id));

                    SysPermission permission = sysPermissionService.selectById(target.getSysPermissionId());
                    target.setSysPermissionCode(permission.getCode());
                    target.setSysPermissionName(permission.getName());
                    sysRolePermissionService.create(target);
                }
            }

        }
    }

    private void updateMenuItems(SysRole entity, String idsStr) {
        if (!Strings.isNullOrEmpty(idsStr)) {
            sysRoleMenuService.deleteByRelevanceId(entity.getId());
            String[] ids = idsStr.split(",");
            for (String id : ids) {
                if (!Strings.isNullOrEmpty(id)) {
                    SysRoleMenu target = new SysRoleMenu();
                    target.setSysRoleId(entity.getId());
                    target.setSysMenuId(Long.valueOf(id));

                    SysMenu sysMenu = sysMenuService.selectById(target.getSysMenuId());
                    target.setSysMenuCode(sysMenu.getCode());
                    target.setSysMenuName(sysMenu.getName());
                    sysRoleMenuService.create(target);
                }
            }

        }
    }

    @Override
    public void updateStatus(SysRole entity, Integer status) throws SysRoleBizEx {
        entity = selectById(entity.getId());
        entity.setStatus(status);
        updateById(entity);
    }
}
