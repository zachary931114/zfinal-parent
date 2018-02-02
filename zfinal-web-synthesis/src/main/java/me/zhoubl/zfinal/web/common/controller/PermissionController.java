package me.zhoubl.zfinal.web.common.controller;

import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.SysPermissionService;
import me.zhoubl.zfinal.synthesis.entity.SysPermission;
import me.zhoubl.zfinal.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 系统权限
 * Created by zhoubl on 2017/5/31.
 */
@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController {
	
    @Autowired
    private SysPermissionService sysPermissionService;

    private void init(ModelMap map) {
		super.setFilePathPrefix("permission");
		super.setListJsonFileName("permission");
		super.setListJsonQueryName("permission");
		map.put("filePathPrefix", super.getFilePathPrefix());
		map.put("listJsonFileName", super.getListJsonFileName());
		map.put("listJsonQueryName", super.getListJsonQueryName());
	}

    @RequestMapping(value = "/list")
    public String list(ModelMap map) {
    	init(map);
        return "/WEB-INF/views/permission/list";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap map) {
    	init(map);
        return "/WEB-INF/views/permission/form";
    }

    @RequestMapping(value = "/edit")
    public String edit(ModelMap map) {
    	init(map);
        if (getIds() != null) {
            SysPermission entity = sysPermissionService.selectById(getIds()[0]);
            map.put("entity", entity);
        }
        return "/WEB-INF/views/permission/form";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public ReturnJson save(SysPermission entity) throws Exception {
        if (entity.getId() == null) {
            sysPermissionService.create(entity);
        } else {
            sysPermissionService.update(entity);
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ReturnJson delete() throws Exception {
        for (Long id : getIds()) {
            sysPermissionService.delete(new SysPermission(id));
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/start")
    @ResponseBody
    public ReturnJson start() throws Exception {
        for (Long id : getIds()) {
            sysPermissionService.updateStatus(new SysPermission(id), 1);
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/stop")
    @ResponseBody
    public ReturnJson stop() throws Exception {
        for (Long id : getIds()) {
            sysPermissionService.updateStatus(new SysPermission(id), 0);
        }
        return new ReturnJson(true, null, null);
    }
}
