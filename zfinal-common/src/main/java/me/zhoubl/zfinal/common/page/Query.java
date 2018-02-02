package me.zhoubl.zfinal.common.page;

/**
 * Created by Zachary on 2016/6/20.
 */
public class Query {
    private String name;    //名称
    private String sql;     //基础sql
    private String nextSql; //group by部分
    private String orderBy; //order by

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getNextSql() {
        return nextSql;
    }

    public void setNextSql(String nextSql) {
        this.nextSql = nextSql;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

}
