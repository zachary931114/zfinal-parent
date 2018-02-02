package me.zhoubl.zfinal.common.page;

import java.util.Map;

/**
 * Created by Zachary on 2016/6/20.
 */
public class Page {
    public static final int PAGESIZE = 20;
    public static final int APPPAGESIZE = 10;
    public static final int WXPAGESIZE = 10;

    private Map<String, String> pageParam;
    private String orderBy;
    private Integer pageIndex;
    private Integer pageSize;
    private String echo;
    private String iDisplayStart;
    private String iDisplayLength;
    private String exportStartNum;
    private String exportEndNum;

    public Map<String, String> getPageParam() {
        return pageParam;
    }

    public void setPageParam(Map<String, String> pageParam) {
        this.pageParam = pageParam;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getEcho() {
        return echo;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(String iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public String getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(String iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public String getExportStartNum() {
        return exportStartNum;
    }

    public void setExportStartNum(String exportStartNum) {
        this.exportStartNum = exportStartNum;
    }

    public String getExportEndNum() {
        return exportEndNum;
    }

    public void setExportEndNum(String exportEndNum) {
        this.exportEndNum = exportEndNum;
    }
}
