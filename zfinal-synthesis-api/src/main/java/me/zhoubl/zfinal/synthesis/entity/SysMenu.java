package me.zhoubl.zfinal.synthesis.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 	系统菜单
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-18
 */
@TableName("sys_menu")
public class SysMenu extends BaseEntity<SysMenu> {

    private static final long serialVersionUID = 1L;

    private Long id;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Version
	private Integer version;
	@TableField(value = "creater_id", fill = FieldFill.INSERT_UPDATE)
	private Long createrId;
	@TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
	private Date createTime;
	@TableField(value = "updater_id", fill = FieldFill.INSERT_UPDATE)
	private Long updaterId;
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
	/**
	 * 状态 0.停用 1.启用
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Integer status;
    /**
     * 上级id
     */
	@TableField("p_id")
	private Long pId;
    /**
     * 编码
     */
	private String code;
    /**
     * 名称
     */
	private String name;
    /**
     * 目标
     */
	private String target;
    /**
     * 地址
     */
	private String url;
    /**
     * 字体图标
     */
	private String icon;
    /**
     * 索引
     */
	@TableField("index_val")
	private Integer indexVal;
    /**
     * 上级序列
     */
	private String seq;


	public Long getId() {
		return id;
	}

	public SysMenu setId(Long id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public SysMenu setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public SysMenu setCreaterId(Long createrId) {
		this.createrId = createrId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public SysMenu setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public SysMenu setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public SysMenu setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public SysMenu setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Long getpId() {
		return pId;
	}

	public SysMenu setpId(Long pId) {
		this.pId = pId;
		return this;
	}

	public String getCode() {
		return code;
	}

	public SysMenu setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public SysMenu setName(String name) {
		this.name = name;
		return this;
	}

	public String getTarget() {
		return target;
	}

	public SysMenu setTarget(String target) {
		this.target = target;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public SysMenu setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public SysMenu setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public Integer getIndexVal() {
		return indexVal;
	}

	public SysMenu setIndexVal(Integer indexVal) {
		this.indexVal = indexVal;
		return this;
	}

	public String getSeq() {
		return seq;
	}

	public SysMenu setSeq(String seq) {
		this.seq = seq;
		return this;
	}

	public static final String ID = "id";

	public static final String VERSION = "version";

	public static final String CREATER_ID = "creater_id";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATER_ID = "updater_id";

	public static final String UPDATE_TIME = "update_time";

	public static final String STATUS = "status";

	public static final String P_ID = "p_id";

	public static final String CODE = "code";

	public static final String NAME = "name";

	public static final String TARGET = "target";

	public static final String URL = "url";

	public static final String ICON = "icon";

	public static final String INDEX_VAL = "index_val";

	public static final String SEQ = "seq";

}
