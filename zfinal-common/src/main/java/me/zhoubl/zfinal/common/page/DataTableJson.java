package me.zhoubl.zfinal.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zachary on 2016/6/20.
 */
public class DataTableJson implements Serializable{
    private List<Query> querys;
    private List<DataTableColumn> columns;

    public List<Query> getQuerys() {
        return querys;
    }

    public void setQuerys(List<Query> querys) {
        this.querys = querys;
    }

    public List<DataTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataTableColumn> columns) {
        this.columns = columns;
    }
}
