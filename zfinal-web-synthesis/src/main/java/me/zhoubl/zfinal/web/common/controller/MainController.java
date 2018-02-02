package me.zhoubl.zfinal.web.common.controller;

import me.zhoubl.zfinal.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by zhoubl on 2017/4/21.
 */
@Controller
@RequestMapping("/admin/main")
public class MainController extends BaseController {

    @RequestMapping
    public String execute(ModelMap map) {
        return "/WEB-INF/views/main";
    }


}