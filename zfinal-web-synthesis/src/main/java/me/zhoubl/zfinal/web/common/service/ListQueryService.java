package me.zhoubl.zfinal.web.common.service;

import me.zhoubl.zfinal.common.page.DataTableJson;
import me.zhoubl.zfinal.common.page.Page;
import me.zhoubl.zfinal.synthesis.entity.SysUser;

import java.util.List;
import java.util.Map;


/**
 * Created by Zachary on 2016/6/20.
 */
public interface ListQueryService {
	public Long getDataTableListSize(Page page, SysUser sysUser, DataTableJson dataTableJson, String queryName,
									 String sqlCondition, String queryStatement);

	public List<Map<String, Object>> getDataTableList(Page page, SysUser sysUser, DataTableJson dataTableJson,
                                                      String queryName, String sqlCondition, String queryStatement);

	public Long getReferDataTableListSize(Page page, SysUser sysUser, DataTableJson dataTableJson, String queryName,
                                          String queryStatement, String p);

	public List<Map<String, Object>> getReferDataTableList(Page page, SysUser sysUser, DataTableJson dataTableJson,
                                                           String queryName, String queryStatement, String p);

	public List<Map<String, Object>> getDataTableListByExport(Page page, SysUser sysUser, DataTableJson dataTableJson,
                                                              String queryName, String sqlCondition, String queryStatement);

}
