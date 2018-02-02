package me.zhoubl.zfinal.web.common.controller;

import java.util.List;

import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.SysMenuService;
import me.zhoubl.zfinal.synthesis.entity.SysMenu;
import me.zhoubl.zfinal.web.common.BaseController;
import me.zhoubl.zfinal.web.common.Constant;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by zhoubl on 2017/5/18.
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/menuList")
    @ResponseBody
    public ReturnJson menuList() {
        List<SysMenu> menuList = null;
        if (SecurityUtils.getSubject().hasRole(Constant.ROLE_SUPPERADMIN)) {
            menuList = sysMenuService.getAll();
        } else {
            menuList = sysMenuService.getAllByUser(getUserInfo());
        }
        return new ReturnJson(true, null, menuList);
    }

}
