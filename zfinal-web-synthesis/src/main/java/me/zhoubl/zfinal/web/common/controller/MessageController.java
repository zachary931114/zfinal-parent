package me.zhoubl.zfinal.web.common.controller;

import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.SysMessageService;
import me.zhoubl.zfinal.synthesis.entity.SysMessage;
import me.zhoubl.zfinal.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by zhoubl on 2017/5/18.
 */
@Controller
@RequestMapping("/admin/message")
public class MessageController extends BaseController {

    @Autowired
    private SysMessageService sysMessageService;
    
    private void init(ModelMap map) {
		super.setFilePathPrefix("message");
		super.setListJsonFileName("message");
		super.setListJsonQueryName("message");
		map.put("filePathPrefix", super.getFilePathPrefix());
		map.put("listJsonFileName", super.getListJsonFileName());
		map.put("listJsonQueryName", super.getListJsonQueryName());
	}

    @RequestMapping(value = "/list")
    public String list(ModelMap map) {
    	init(map);
        return "/WEB-INF/views/message/list";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ReturnJson delete() throws Exception {
        for (Long id : getIds()) {
            sysMessageService.delete(new SysMessage(id));
        }
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/getListByUser")
    @ResponseBody
    public ReturnJson getListByUser() throws Exception {
        return new ReturnJson(true, null, sysMessageService.getListByUserId(super.getUserInfo().getId()));
    }

    @RequestMapping(value = "/getListSizeByUser")
    @ResponseBody
    public ReturnJson getListSizeByUser() throws Exception {
        return new ReturnJson(true, null, sysMessageService.getListSizeByUserId(super.getUserInfo().getId()));
    }

    @RequestMapping(value = "/getUrl")
    @ResponseBody
    public ReturnJson getUrl(@RequestParam("id") Long id) throws Exception {
        SysMessage entity = sysMessageService.selectById(id);
        return new ReturnJson(true, null, entity.getUrl());
    }

}
