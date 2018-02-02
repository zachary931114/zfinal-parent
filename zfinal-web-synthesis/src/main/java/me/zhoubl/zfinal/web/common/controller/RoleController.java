package me.zhoubl.zfinal.web.common.controller;

import java.util.List;

import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.SysRoleMenuService;
import me.zhoubl.zfinal.synthesis.api.SysRolePermissionService;
import me.zhoubl.zfinal.synthesis.api.SysRoleService;
import me.zhoubl.zfinal.synthesis.entity.SysRole;
import me.zhoubl.zfinal.synthesis.entity.SysRoleMenu;
import me.zhoubl.zfinal.synthesis.entity.SysRolePermission;
import me.zhoubl.zfinal.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 系统角色
 * Created by zhoubl on 2017/5/31.
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {
	
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    private void init(ModelMap map) {
		super.setFilePathPrefix("role");
		super.setListJsonFileName("role");
		super.setListJsonQueryName("role");
		map.put("filePathPrefix", super.getFilePathPrefix());
		map.put("listJsonFileName", super.getListJsonFileName());
		map.put("listJsonQueryName", super.getListJsonQueryName());
	}

    @RequestMapping(value = "/list")
    public String list(ModelMap map) {
    	init(map);
        return "/WEB-INF/views/role/list";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap map) {
    	init(map);
    	return "/WEB-INF/views/role/form";
    }

    @RequestMapping(value = "/edit")
    public String edit(ModelMap map) {
    	init(map);
    	if (getIds() != null) {
            SysRole entity = sysRoleService.selectById(getIds()[0]);
            map.put("entity", entity);

            StringBuilder permissionIds = new StringBuilder();
            StringBuilder permissionNames = new StringBuilder();
            List<SysRolePermission> sysRolePermissions = sysRolePermissionService.getAllByRelevanceId(entity.getId());
            for (SysRolePermission sysRolePermission : sysRolePermissions) {
                permissionIds.append("," + sysRolePermission.getSysPermissionId());
                permissionNames.append("," + sysRolePermission.getSysPermissionName());
            }
            if (permissionIds.length() > 0) {
                map.put("permissionIds", permissionIds.substring(1));
            }
            if (permissionNames.length() > 0) {
                map.put("permissionNames", permissionNames.substring(1));
            }


            StringBuilder menuIds = new StringBuilder();
            StringBuilder menuNames = new StringBuilder();
            List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.getAllByRelevanceId(entity.getId());
            for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
                menuIds.append("," + sysRoleMenu.getSysMenuId());
                menuNames.append("," + sysRoleMenu.getSysMenuName());
            }
            if (menuIds.length() > 0) {
                map.put("menuIds", menuIds.substring(1));
            }
            if (menuNames.length() > 0) {
                map.put("menuNames", menuNames.substring(1));
            }

        }
        return "/WEB-INF/views/role/form";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public ReturnJson save(SysRole entity, @RequestParam("permissionIds") String permissionIds, @RequestParam("menuIds") String menuIds) throws Exception {
        if (entity.getId() == null) {
            sysRoleService.create(entity, permissionIds, menuIds);
        } else {
            sysRoleService.update(entity, permissionIds, menuIds);
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ReturnJson delete() throws Exception {
        for (Long id : getIds()) {
            sysRoleService.delete(new SysRole(id));
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/start")
    @ResponseBody
    public ReturnJson start() throws Exception {
        for (Long id : getIds()) {
            sysRoleService.updateStatus(new SysRole(id), 1);
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/stop")
    @ResponseBody
    public ReturnJson stop() throws Exception {
        for (Long id : getIds()) {
            sysRoleService.updateStatus(new SysRole(id), 0);
        }
        return new ReturnJson(true, null, null);
    }
}
