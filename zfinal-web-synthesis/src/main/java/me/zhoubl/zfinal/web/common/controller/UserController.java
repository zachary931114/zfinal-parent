package me.zhoubl.zfinal.web.common.controller;

import java.util.List;
import java.util.UUID;

import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.SysUserRoleService;
import me.zhoubl.zfinal.synthesis.api.SysUserService;
import me.zhoubl.zfinal.synthesis.entity.CommonFiles;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import me.zhoubl.zfinal.synthesis.entity.SysUserRole;
import me.zhoubl.zfinal.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

/**
 * 系统用户 Created by zhoubl on 2017/5/20.
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	private void init(ModelMap map) {
		super.setFilePathPrefix("user");
		super.setListJsonFileName("user");
		super.setListJsonQueryName("user");
		map.put("filePathPrefix", super.getFilePathPrefix());
		map.put("listJsonFileName", super.getListJsonFileName());
		map.put("listJsonQueryName", super.getListJsonQueryName());
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap map) {
		init(map);
		return "/WEB-INF/views/user/list";
	}

	@RequestMapping(value = "/add")
	public String add(ModelMap map) {
		init(map);
		return "/WEB-INF/views/user/form";
	}

	@RequestMapping(value = "/alterPwd")
	public String alterPwd(ModelMap map) {
		return "/WEB-INF/views/user/alterPwd";
	}

	@RequestMapping(value = "/savePwd")
	@ResponseBody
	public ReturnJson savePwd(@RequestParam("pwd") String pwd, @RequestParam("nPwd") String nPwd) throws Exception {
		sysUserService.updatePwd(new SysUser(getUserInfo().getId()), pwd, nPwd);
		return new ReturnJson(true, null, null);
	}

	@RequestMapping(value = "/edit")
	public String edit(ModelMap map) {
		init(map);
		if (getIds() != null) {
			SysUser entity = sysUserService.selectById(getIds()[0]);
			map.put("entity", entity);
			if (!Strings.isNullOrEmpty(entity.getFileUuid())) {
				map.put("uuid", entity.getFileUuid());
			} else {
				map.put("uuid", UUID.randomUUID());
			}
			StringBuilder roleIds = new StringBuilder();
			StringBuilder roleNames = new StringBuilder();
			List<SysUserRole> sysUserRoles = sysUserRoleService.getAllByRelevanceId(entity.getId());
			for (SysUserRole sysUserRole : sysUserRoles) {
				roleIds.append("," + sysUserRole.getSysRoleId());
				roleNames.append("," + sysUserRole.getSysRoleName());
			}
			if (roleIds.length() > 0) {
				map.put("roleIds", roleIds.substring(1));
			}
			if (roleNames.length() > 0) {
				map.put("roleNames", roleNames.substring(1));
			}
		}
		return "/WEB-INF/views/user/form";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public ReturnJson save(SysUser entity, @RequestParam("roleIds") String roleIds) throws Exception {
		SysUser user = null;
		if (entity.getId() == null) {
			user = sysUserService.create(entity, roleIds);
		} else {
			user = sysUserService.update(entity, roleIds);
		}
		CommonFiles cf = commonFilesService.getOneByUUID(user.getFileUuid(), "headPortrait");
		if (cf != null) {
			getUserInfo().setHeadPortraitFilePath(cf.getFilePath());
		}
		return new ReturnJson(true, null, null);
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public ReturnJson delete() throws Exception {
		for (Long id : getIds()) {
			sysUserService.delete(new SysUser(id));
		}
		return new ReturnJson(true, null, null);
	}

	@RequestMapping(value = "/start")
	@ResponseBody
	public ReturnJson start() throws Exception {
		for (Long id : getIds()) {
			sysUserService.updateStatus(new SysUser(id), 1);
		}
		return new ReturnJson(true, null, null);
	}

	@RequestMapping(value = "/stop")
	@ResponseBody
	public ReturnJson stop() throws Exception {
		for (Long id : getIds()) {
			sysUserService.updateStatus(new SysUser(id), 0);
		}
		return new ReturnJson(true, null, null);
	}

}
