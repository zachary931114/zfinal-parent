package me.zhoubl.zfinal.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.zhoubl.zfinal.common.CommonConfig;
import me.zhoubl.zfinal.common.ex.BizEx;
import me.zhoubl.zfinal.common.page.*;
import me.zhoubl.zfinal.common.utils.UtilBean;
import me.zhoubl.zfinal.common.utils.UtilCodec;
import me.zhoubl.zfinal.common.utils.UtilExcel;
import me.zhoubl.zfinal.common.utils.UtilServlet;
import me.zhoubl.zfinal.common.utils.serialization.FastJsonSerializer;
import me.zhoubl.zfinal.common.web.WebCommonConfig;
import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.CommonFilesService;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import me.zhoubl.zfinal.web.common.service.ListQueryService;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

/**
 * Created by zhoubl on 2017/4/21.
 */
public class BaseController implements Serializable {

	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	protected ListQueryService listQueryService;

	@Autowired
	protected CommonFilesService commonFilesService;

	protected ThreadLocal<Page> page = new ThreadLocal<Page>(); // 列表初始对象

	private ThreadLocal<Long[]> ids = new ThreadLocal<Long[]>(); // 列表所选id

	private ThreadLocal<String> queryStatement = new ThreadLocal<String>(); // 列表查询条件
	private ThreadLocal<String> dataTableData = new ThreadLocal<String>(); // 列表信息数据
	private ThreadLocal<String> exportStartNum = new ThreadLocal<String>(); // 导出开始数量
	private ThreadLocal<String> exportEndNum = new ThreadLocal<String>(); // 导出结束数量

	protected ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	protected ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	protected ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();

	private ThreadLocal<String> listJsonFileName = new ThreadLocal<String>(); // 列表json文件名称
	private ThreadLocal<String> listJsonQueryName = new ThreadLocal<String>(); // 列表查询语句名称
	protected ThreadLocal<String> listJsonSqlCondition = new ThreadLocal<String>(); // 列表查询条件

	// 文件路径前缀
	private ThreadLocal<String> filePathPrefix = new ThreadLocal<String>();

	// 文件根路径
	private String filesPath;

	// 文件路径访问url
	private String filesUrl;

	// 项目url
	private String projectUrl;

	protected final static String MESSAGE = "\"isSuccess\":{0},\"message\":\"{1}\",\"data\":{2}";

	protected String ERROR_INFO = "系统内部错误"; // 提示错误信息

	/**
	 * 初始化
	 */

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(value = "ids", required = false) Long[] ids,
			@RequestParam(value = "dataTableData", required = false) String dataTableData,
			@RequestParam(value = "queryStatement", required = false) String queryStatement,
			@RequestParam(value = "listJsonFileName", required = false) String listJsonFileName,
			@RequestParam(value = "listJsonQueryName", required = false) String listJsonQueryName,
			@RequestParam(value = "listJsonSqlCondition", required = false) String listJsonSqlCondition,
			@RequestParam(value = "exportStartNum", required = false) String exportStartNum,
			@RequestParam(value = "exportEndNum", required = false) String exportEndNum) {
		this.setRequest(request);
		this.setResponse(response);
		this.setSession(request.getSession());

		this.setIds(ids);
		this.setDataTableData(dataTableData);
		if (!Strings.isNullOrEmpty(queryStatement)) {
			try {
				this.setQueryStatement(new String(UtilCodec.decodeBase64(queryStatement), "UTF-8"));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			this.setQueryStatement(null);
		}

		this.setListJsonFileName(listJsonFileName);
		this.setListJsonQueryName(listJsonQueryName);

		if (!Strings.isNullOrEmpty(listJsonSqlCondition)) {
			try {
				this.listJsonSqlCondition.set(new String(UtilCodec.decodeBase64(listJsonSqlCondition), "UTF-8"));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			this.listJsonSqlCondition.set(null);
		}

		this.setExportStartNum(exportStartNum);
		this.setExportEndNum(exportEndNum);

		this.setFilesUrl(CommonConfig.sysMap.get("filesUrl"));
		this.setProjectUrl(CommonConfig.sysMap.get("projectUrl"));
		this.setFilesPath(CommonConfig.sysMap.get("FILES_PATH"));

		modelMap.put("servletPath", request.getServletPath());
		modelMap.put("uuid", UUID.randomUUID());
		modelMap.put("filesUrl", this.getFilesUrl());
		modelMap.put("projectUrl", this.getProjectUrl());
		modelMap.put("BaiduWEBAK", CommonConfig.commonMap.get("BaiduWEBAK"));
	}

	@ExceptionHandler
	public String exp(Exception ex, HttpServletRequest req) {

		// 根据不同错误转向不同页面
		if (ex instanceof BizEx) {
			if (UtilServlet.isAjaxRequest(req)) {
				sendJson(FastJsonSerializer.toJSONString(new ReturnJson(false, ex.getMessage(), null)));
			} else {
				req.setAttribute("type", "BizEx");
				req.setAttribute("errMessage", ex.getMessage());
				return "/WEB-INF/views/error/500";
			}
		} else {
			if (UtilServlet.isAjaxRequest(req)) {
				sendJson(FastJsonSerializer.toJSONString(new ReturnJson(false, ERROR_INFO, null)));
				logger.error(ex.getMessage(), ex);
			} else {
				req.setAttribute("type", "Exception");
				req.setAttribute("errMessage", ERROR_INFO);
				logger.error(ex.getMessage(), ex);
				return "/WEB-INF/views/error/500";
			}
		}

		return null;

	}

	public void sendJson(String json) {
		getResponse().setContentType("text/html;charset=utf-8");
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.print(json);
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				out = null;
			}
		}
	}

	/**
	 * 文件下载
	 *
	 * @param file
	 * @param mimeType
	 * @param showTitle
	 */
	public void sendFile(File file, String mimeType, String showTitle) {
		if (Strings.isNullOrEmpty(mimeType)) {
			mimeType = "application/octet-stream";
		}
		getResponse().setContentType(mimeType);
		getResponse().setCharacterEncoding("UTF-8");
		try {
			getResponse().setHeader("Content-Disposition",
					"attachment; filename=" + java.net.URLEncoder.encode(showTitle, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		getResponse().addHeader("Content-Length", String.valueOf(file.length()));
		ServletOutputStream out = null;
		FileInputStream is = null;
		try {
			out = getResponse().getOutputStream();
			is = new FileInputStream(file);
			byte buffer[] = new byte[2048];
			int bytes = 0;
			while ((bytes = is.read(buffer)) != -1) {
				out.write(buffer, 0, bytes);
			}
		} catch (IOException e) {
		} finally {
			try {
				if (is != null)
					is.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
			} finally {
				is = null;
				out = null;
			}
		}
	}

	public SysUser getUserInfo() {
		return (SysUser) SecurityUtils.getSubject().getSession().getAttribute(WebCommonConfig.USERINFO);
	}

	@RequestMapping(value = "/getListTitleJson", method = RequestMethod.POST)
	@ResponseBody
	public ReturnJson getListTitleJson() throws Exception {
		DataTableJson dataTableJson = getDataTableJson();
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

	@RequestMapping(value = "/getListJson", method = RequestMethod.POST)
	@ResponseBody
	public ReturnJson getListJson() throws Exception {
		initDataTables();
		DataTableJson dataTableJson = getDataTableJson();
		Long count = listQueryService.getDataTableListSize(getPage(), getUserInfo(), dataTableJson,
				getListJsonQueryName(), getListJsonSqlCondition(), getQueryStatement());
		List<Map<String, Object>> datas = listQueryService.getDataTableList(getPage(), getUserInfo(), dataTableJson,
				getListJsonQueryName(), getListJsonSqlCondition(), getQueryStatement());
		return sendListJson(datas, count, Map.class, dataTableJson);
	}

	@RequestMapping(value = "/export")
	public void export(@RequestParam("title") String title) throws Exception {
		initDataTables();
		DataTableJson dataTableJson = getDataTableJson();
		List<String> titleList = new ArrayList<String>();
		for (DataTableColumn column : dataTableJson.getColumns()) {
			if (column.getVisible()) {
				titleList.add(column.getTitle());
			}
		}

		String[][] datasArr = exportData(dataTableJson);

		getResponse().setContentType("application/vnd.ms-excel");
		getResponse().setCharacterEncoding("UTF-8");
		try {
			getResponse().setHeader("Content-Disposition",
					"attachment; filename=" + java.net.URLEncoder.encode(title, "UTF-8") + ".xls");
		} catch (UnsupportedEncodingException e) {
		}
		UtilExcel.exportExcel(title, titleList.toArray(new String[titleList.size()]), datasArr,
				getResponse().getOutputStream());
	}

	protected String[][] exportData(DataTableJson dataTableJson) throws Exception {
		List<Map<String, Object>> datas = listQueryService.getDataTableListByExport(getPage(), getUserInfo(),
				dataTableJson, getListJsonQueryName(), getListJsonSqlCondition(), getQueryStatement());
		return convertData(datas, Map.class, dataTableJson, false);
	}

	protected ReturnJson sendListJson(List<?> datas, Long count, Class<?> c, DataTableJson dataTableJson)
			throws Exception {
		DataTableVo dtv = new DataTableVo();
		dtv.setAaData(convertData(datas, c, dataTableJson, true));
		dtv.setsEcho(getPage().getEcho());
		dtv.setiTotalRecords(count);
		dtv.setiTotalDisplayRecords(count);
		return new ReturnJson(true, null, dtv);
	}

	protected String[][] convertData(List list, Class<?> c, DataTableJson dataTableJson, boolean isShowId)
			throws Exception {
		String[][] aaData = new String[list.size()][];

		if (c.isAssignableFrom(Map.class)) {
			List<Map> nlist = new ArrayList<Map>();
			nlist = list;
			for (int i = 0; i < nlist.size(); i++) {
				Map objs = nlist.get(i);
				aaData[i] = new String[objs.size()];
				int flag = 0;
				for (Object val : objs.values()) {
					if (val != null) {
						aaData[i][flag] = val.toString();
					} else {
						aaData[i][flag] = "";
					}
					flag++;
				}
			}
		} else {
			List<DataTableColumn> clist = new ArrayList<DataTableColumn>();
			clist.addAll(dataTableJson.getColumns());
			if (isShowId) {
				DataTableColumn dtc = new DataTableColumn();
				dtc.setColumn("id");
				clist.add(0, dtc);
				clist.add(0, dtc);
			}
			for (int i = 0; i < list.size(); i++) {
				Object object = list.get(i);
				BeanWrapper bw = new BeanWrapperImpl(object);
				aaData[i] = new String[clist.size()];
				for (int j = 0; j < clist.size(); j++) {
					DataTableColumn column = clist.get(j);
					String field = column.getColumn();
					if (!Strings.isNullOrEmpty(field)) {
						try {
							Object val = bw.getPropertyValue(field);
							if (val != null) {
								aaData[i][j] = val.toString();
							} else {
								aaData[i][j] = "";
							}
						} catch (Exception e) {
							aaData[i][j] = "";
						}
					} else {
						aaData[i][j] = "";
					}
				}
			}
		}

		return aaData;
	}

	protected DataTableJson getDataTableJson() throws IOException {

		String isReleaseVersion = CommonConfig.sysMap.get("isReleaseVersion");
		if (Strings.isNullOrEmpty(isReleaseVersion)) {
			isReleaseVersion = "0";
		}

		DataTableJson dataTableJson = null;
		if (WebCommonConfig.dataTableJsonMap.containsKey(getListJsonFileName()) && !isReleaseVersion.equals("0")) {
			dataTableJson = WebCommonConfig.dataTableJsonMap.get(getListJsonFileName());
		} else {

			String path = getSession().getServletContext().getRealPath(WebCommonConfig.LISTJSONPATH) + "/"
					+ getListJsonFileName() + ".json";
			String json = FileUtils.readFileToString(new File(path), "utf-8");

			dataTableJson = FastJsonSerializer.toBean(json, DataTableJson.class);

			WebCommonConfig.dataTableJsonMap.put(getListJsonFileName(), dataTableJson);
		}
		return dataTableJson;
	}

	protected DataTableJson getDataTableJson(String listJsonFileName) throws IOException {

		String isReleaseVersion = CommonConfig.sysMap.get("isReleaseVersion");
		if (Strings.isNullOrEmpty(isReleaseVersion)) {
			isReleaseVersion = "0";
		}

		DataTableJson dataTableJson = null;
		if (WebCommonConfig.dataTableJsonMap.containsKey(listJsonFileName) && !isReleaseVersion.equals("0")) {
			dataTableJson = WebCommonConfig.dataTableJsonMap.get(listJsonFileName);
		} else {

			String path = getSession().getServletContext().getRealPath(WebCommonConfig.LISTJSONPATH) + "/"
					+ listJsonFileName + ".json";
			String json = FileUtils.readFileToString(new File(path), "utf-8");

			dataTableJson = FastJsonSerializer.toBean(json, DataTableJson.class);

			WebCommonConfig.dataTableJsonMap.put(listJsonFileName, dataTableJson);
		}
		return dataTableJson;
	}

	protected void initDataTables() {
		StringTokenizer st = new StringTokenizer(getDataTableData(), "&");
		Map<String, String> mapParm = new HashMap<String, String>();
		while (st.hasMoreElements()) {
			String[] value = st.nextToken().split("=");
			if (value.length > 1) {
				mapParm.put(value[0], value[1]);
			}
		}
		getPage().setEcho(mapParm.get("sEcho"));
		String[] sColumns = mapParm.get("sColumns").split("%2C");// 列名
		if (mapParm.containsKey("iSortCol_0")) {
			int iSortCol = Integer.valueOf(mapParm.get("iSortCol_0"));// 第几列排序
			if (iSortCol != 0) {
				String sSortDir = mapParm.get("sSortDir_0");// 排列规则
				getPage().setOrderBy(sColumns[iSortCol] + " " + sSortDir);
			}
		}
		getPage().setiDisplayStart(mapParm.get("iDisplayStart"));
		getPage().setiDisplayLength(mapParm.get("iDisplayLength"));
		getPage().setPageSize(Integer.valueOf(getPage().getiDisplayLength()));
		getPage().setPageIndex(Integer.parseInt(getPage().getiDisplayStart()) / getPage().getPageSize() + 1);
		getPage().setExportStartNum(getExportStartNum());
		getPage().setExportEndNum(getExportEndNum());
		getPage().setPageParam(mapParm);
	}

	public Page getPage() {
		Page p = page.get();
		if (p == null) {
			p = new Page();
			setPage(p);
		}
		return p;
	}

	public void setPage(Page page) {
		this.page.set(page);
	}

	public Long[] getIds() {
		return ids.get();
	}

	public void setIds(Long[] ids) {
		this.ids.set(ids);
	}

	public String getQueryStatement() {
		return queryStatement.get();
	}

	public void setQueryStatement(String queryStatement) {
		this.queryStatement.set(queryStatement);
	}

	public String getDataTableData() {
		return dataTableData.get();
	}

	public void setDataTableData(String dataTableData) {
		this.dataTableData.set(dataTableData);
	}

	public String getExportStartNum() {
		return exportStartNum.get();
	}

	public void setExportStartNum(String exportStartNum) {
		this.exportStartNum.set(exportStartNum);
	}

	public String getExportEndNum() {
		return exportEndNum.get();
	}

	public void setExportEndNum(String exportEndNum) {
		this.exportEndNum.set(exportEndNum);
	}

	public HttpServletRequest getRequest() {
		return request.get();
	}

	public void setRequest(HttpServletRequest request) {
		this.request.set(request);
	}

	public HttpServletResponse getResponse() {
		return response.get();
	}

	public void setResponse(HttpServletResponse response) {
		this.response.set(response);
	}

	public HttpSession getSession() {
		return session.get();
	}

	public void setSession(HttpSession session) {
		this.session.set(session);
	}

	public String getListJsonFileName() {
		return listJsonFileName.get();
	}

	public void setListJsonFileName(String listJsonFileName) {
		this.listJsonFileName.set(listJsonFileName);
	}

	public String getListJsonQueryName() {
		return listJsonQueryName.get();
	}

	public void setListJsonQueryName(String listJsonQueryName) {
		this.listJsonQueryName.set(listJsonQueryName);
	}

	public String getListJsonSqlCondition() {
		return listJsonSqlCondition.get();
	}

	public void setListJsonSqlCondition(String listJsonSqlCondition) {
		if (!Strings.isNullOrEmpty(listJsonSqlCondition)) {
			this.listJsonSqlCondition.set(UtilCodec.encodeBase64(listJsonSqlCondition));
		} else {
			this.listJsonSqlCondition.set(null);
		}
	}

	public String getFilePathPrefix() {
		return filePathPrefix.get();
	}

	public void setFilePathPrefix(String filePathPrefix) {
		this.filePathPrefix.set(filePathPrefix);
	}

	public String getFilesPath() {
		return filesPath;
	}

	public void setFilesPath(String filesPath) {
		this.filesPath = filesPath;
	}

	public String getFilesUrl() {
		return filesUrl;
	}

	public void setFilesUrl(String filesUrl) {
		this.filesUrl = filesUrl;
	}

	public String getProjectUrl() {
		return projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}
}
