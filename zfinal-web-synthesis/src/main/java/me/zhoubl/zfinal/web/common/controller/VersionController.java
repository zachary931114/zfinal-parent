package me.zhoubl.zfinal.web.common.controller;

import java.util.UUID;

import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.CommonVersionService;
import me.zhoubl.zfinal.synthesis.entity.CommonVersion;
import me.zhoubl.zfinal.synthesis.enums.CommonVersionType;
import me.zhoubl.zfinal.web.common.BaseController;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

/**
 * 公共版本管理
 *  Created by zhoubl on 2017/5/31.
 */
@Controller
@RequestMapping("/admin/version")
public class VersionController extends BaseController {

	@Autowired
	private CommonVersionService commonVersionService;

	private void init(ModelMap map) {
		super.setFilePathPrefix("version");
		super.setListJsonFileName("version");
		super.setListJsonQueryName("version");
		map.put("filePathPrefix", super.getFilePathPrefix());
		map.put("listJsonFileName", super.getListJsonFileName());
		map.put("listJsonQueryName", super.getListJsonQueryName());
	}

	@RequestMapping(value = "/list")
	public String list(ModelMap map) {
		init(map);
		return "/WEB-INF/views/version/list";
	}

	@RequestMapping(value = "/add")
	public String add(ModelMap map) {
		init(map);
		map.put("commonVersionTypeList", EnumUtils.getEnumList(CommonVersionType.class));
		return "/WEB-INF/views/version/form";
	}

	@RequestMapping(value = "/edit")
	public String edit(ModelMap map) {
		init(map);
		if (getIds() != null) {
			CommonVersion entity = commonVersionService.selectById(getIds()[0]);
			map.put("entity", entity);
			if (!Strings.isNullOrEmpty(entity.getFileUuid())) {
                map.put("uuid", entity.getFileUuid());
            } else {
                map.put("uuid", UUID.randomUUID());
            }
			map.put("commonVersionTypeList", EnumUtils.getEnumList(CommonVersionType.class));
		}
		return "/WEB-INF/views/version/form";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public ReturnJson save(CommonVersion entity) throws Exception {
		if (entity.getId() == null) {
			commonVersionService.create(entity);
		} else {
			commonVersionService.update(entity);
		}
		return new ReturnJson(true, null, null);
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public ReturnJson delete() throws Exception {
		for (Long id : getIds()) {
			commonVersionService.delete(new CommonVersion(id));
		}
		return new ReturnJson(true, null, null);
	}

	@RequestMapping(value = "/start")
	@ResponseBody
	public ReturnJson start() throws Exception {
		for (Long id : getIds()) {
			commonVersionService.updateStatus(new CommonVersion(id), 1);
		}
		return new ReturnJson(true, null, null);
	}

	@RequestMapping(value = "/stop")
	@ResponseBody
	public ReturnJson stop() throws Exception {
		for (Long id : getIds()) {
			commonVersionService.updateStatus(new CommonVersion(id), 0);
		}
		return new ReturnJson(true, null, null);
	}

}
