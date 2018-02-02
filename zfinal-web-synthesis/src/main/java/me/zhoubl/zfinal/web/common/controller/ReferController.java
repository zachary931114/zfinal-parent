package me.zhoubl.zfinal.web.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.zhoubl.zfinal.common.page.DataTableColumn;
import me.zhoubl.zfinal.common.page.DataTableColumnVo;
import me.zhoubl.zfinal.common.page.DataTableJson;
import me.zhoubl.zfinal.common.page.Page;
import me.zhoubl.zfinal.common.utils.UtilBean;
import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by zhoubl on 2017/4/21.
 */
@Controller
@RequestMapping("/admin/refer")
public class ReferController extends BaseController {

    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "listJsonFileName", required = true) String listJsonFileName,
                       @RequestParam(value = "listJsonQueryName", required = true) String listJsonQueryName,
                       @RequestParam(value = "eIds", required = true) String eIds,
                       @RequestParam(value = "multiple", required = true) String multiple,
                       @RequestParam(value = "p", required = false) String p,
                       @RequestParam(value = "fn", required = false) String fn, ModelMap map) {
        map.put("listJsonFileName", listJsonFileName);
        map.put("listJsonQueryName", listJsonQueryName);
        map.put("eIds", eIds);
        map.put("multiple", multiple);
        map.put("p", p);
        map.put("fn", fn);
        return "/WEB-INF/views/common/refer";
    }

    @RequestMapping(value = "/getReferTitleJson")
    @ResponseBody
    public ReturnJson getReferTitleJson(@RequestParam(value = "listJsonFileName", required = true) String listJsonFileName) throws Exception {
        DataTableJson dataTableJson = getDataTableJson(listJsonFileName);
        List<DataTableColumn> columns = new ArrayList<DataTableColumn>();
        DataTableColumn dataTableColumn = new DataTableColumn();
        dataTableColumn.setTitle("序列");
        dataTableColumn.setOrderable(false);
        dataTableColumn.setWidth(50);
        columns.add(dataTableColumn);
        dataTableColumn = new DataTableColumn();
        dataTableColumn.setTitle("");
        dataTableColumn.setOrderable(false);
        dataTableColumn.setWidth(20);
        columns.add(dataTableColumn);
        // dataTableColumn = new DataTableColumn();
        // dataTableColumn.setTitle("操作");
        // dataTableColumn.setOrderable(false);
        // dataTableColumn.setWidth(150);
        // columns.add(dataTableColumn);
        int sumW = 55;
        for (DataTableColumn column : dataTableJson.getColumns()) {
            dataTableColumn = new DataTableColumn();
            UtilBean.copyPropertiesIgnoreNull(column, dataTableColumn);
            dataTableColumn.setWidth(dataTableColumn.getWidth() * 15);
            dataTableColumn.setColumn(null);
            columns.add(dataTableColumn);
            sumW += dataTableColumn.getWidth();
        }
        DataTableColumnVo dataTableColumnVo = new DataTableColumnVo();
        dataTableColumnVo.setColumnList(columns);
        dataTableColumnVo.setSumWidth(String.valueOf(sumW));
        dataTableColumnVo.setiDisplayStart(String.valueOf(0));
        dataTableColumnVo.setiDisplayLength(String.valueOf(Page.PAGESIZE));
        return new ReturnJson(true, null, dataTableColumnVo);
    }

    @RequestMapping(value = "/getReferJson")
    @ResponseBody
    public ReturnJson getReferJson(@RequestParam(value = "listJsonFileName", required = true) String listJsonFileName,
                             @RequestParam(value = "listJsonQueryName", required = true) String listJsonQueryName,
                             @RequestParam(value = "p", required = false) String p) throws Exception {
        initDataTables();
        DataTableJson dataTableJson = getDataTableJson(listJsonFileName);
        Long count = listQueryService.getReferDataTableListSize(getPage(), getUserInfo(), dataTableJson, listJsonQueryName, getQueryStatement(), p);
        List<Map<String, Object>> datas = listQueryService.getReferDataTableList(getPage(), getUserInfo(), dataTableJson, listJsonQueryName, getQueryStatement(), p);
        return sendListJson(datas, count, Map.class, dataTableJson);
    }


}