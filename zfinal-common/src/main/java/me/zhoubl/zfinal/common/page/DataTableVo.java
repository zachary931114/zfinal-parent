package me.zhoubl.zfinal.common.page;

import java.io.Serializable;

/**
 * Created by Zachary on 2016/6/20.
 */
public class DataTableVo implements Serializable{
    private long iTotalRecords;
    private long iTotalDisplayRecords;
    private String sEcho;
    private String[][] aaData;

    public long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public String[][] getAaData() {
        return aaData;
    }

    public void setAaData(String[][] aaData) {
        this.aaData = aaData;
    }
}
