package me.zhoubl.zfinal.common.page;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Zachary on 2016/6/20.
 */
public class DataTableColumnVo implements Serializable {
    private String iDisplayStart;
    private String iDisplayLength;
    private String sumWidth;
    private List<DataTableColumn> columnList;

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

    public List<DataTableColumn> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<DataTableColumn> columnList) {
        this.columnList = columnList;
    }

    public String getSumWidth() {
        return sumWidth;
    }

    public void setSumWidth(String sumWidth) {
        this.sumWidth = sumWidth;
    }
}
