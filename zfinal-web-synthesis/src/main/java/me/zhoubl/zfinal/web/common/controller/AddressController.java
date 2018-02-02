package me.zhoubl.zfinal.web.common.controller;

import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.CommonAddressService;
import me.zhoubl.zfinal.synthesis.entity.CommonAddress;
import me.zhoubl.zfinal.synthesis.enums.CommonAddressType;
import me.zhoubl.zfinal.web.common.BaseController;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 公共地理地址
 * Created by zhoubl on 2017/5/31.
 */
@Controller
@RequestMapping("/admin/address")
public class AddressController extends BaseController {

    @Autowired
    private CommonAddressService commonAddressService;

    private void init(ModelMap map) {
		super.setFilePathPrefix("address");
		super.setListJsonFileName("address");
		super.setListJsonQueryName("address");
		map.put("filePathPrefix", super.getFilePathPrefix());
		map.put("listJsonFileName", super.getListJsonFileName());
		map.put("listJsonQueryName", super.getListJsonQueryName());
	}

    @RequestMapping(value = "/list")
    public String list(ModelMap map) {
    	init(map);
        return "/WEB-INF/views/address/list";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap map) {
    	init(map);
        map.put("commonAddressTypeList", EnumUtils.getEnumList(CommonAddressType.class));
        return "/WEB-INF/views/address/form";
    }

    @RequestMapping(value = "/edit")
    public String edit(ModelMap map) {
    	init(map);
        if (getIds() != null) {
            CommonAddress entity = commonAddressService.selectById(getIds()[0]);
            map.put("entity", entity);
            map.put("commonAddressTypeList", EnumUtils.getEnumList(CommonAddressType.class));
        }
        return "/WEB-INF/views/address/form";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public ReturnJson save(CommonAddress entity) throws Exception {
        if (entity.getId() == null) {
            commonAddressService.create(entity);
        } else {
            commonAddressService.update(entity);
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ReturnJson delete() throws Exception {
        for (Long id : getIds()) {
            commonAddressService.delete(new CommonAddress(id));
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/start")
    @ResponseBody
    public ReturnJson start() throws Exception {
        for (Long id : getIds()) {
            commonAddressService.updateStatus(new CommonAddress(id), 1);
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/stop")
    @ResponseBody
    public ReturnJson stop() throws Exception {
        for (Long id : getIds()) {
            commonAddressService.updateStatus(new CommonAddress(id), 0);
        }
        return new ReturnJson(true, null, null);
    }

}
