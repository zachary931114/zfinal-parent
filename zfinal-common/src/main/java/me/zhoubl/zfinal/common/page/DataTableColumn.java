package me.zhoubl.zfinal.common.page;

import java.io.Serializable;

/**
 * Created by Zachary on 2016/6/20.
 */
public class DataTableColumn implements Serializable {

	/**
	 * 数据类型
	 */
	private String type = "String";

	/**
	 * 显示的标题
	 */
	private String title;

	/**
	 * 字段
	 */
	private String name;

	/**
	 * 宽度
	 */
	private Integer width = 15;

	/**
	 * align 样式 对齐方式 center left right
	 */
	private String className = "center";

	/**
	 * 是否排序
	 */
	private boolean orderable = true;

	/**
	 * 字段是否显示
	 */
	private boolean visible = true;

	/**
	 * 字段
	 */
	private String column;

	/**
	 * map
	 */
	private String[] keyValue;

	/**
	 * 是否支持搜索
	 */
	private boolean isSearch = true;

	/**
	 * 日期格式
	 */
	private String format = "yyyy-MM-dd";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean getOrderable() {
		return orderable;
	}

	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String[] getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String[] keyValue) {
		this.keyValue = keyValue;
	}

	public boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(boolean search) {
		isSearch = search;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}