package me.zhoubl.zfinal.synthesis.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 	系统角色
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
@TableName("sys_role")
public class SysRole extends BaseEntity<SysRole> {

	public SysRole(){}
	public SysRole(Long id){
		this.id = id;
	}

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
     * 编码
     */
	private String code;
    /**
     * 名称
     */
	private String name;
    /**
     * 备注
     */
	private String remark;


	public Long getId() {
		return id;
	}

	public SysRole setId(Long id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public SysRole setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public SysRole setCreaterId(Long createrId) {
		this.createrId = createrId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public SysRole setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public SysRole setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public SysRole setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public SysRole setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getCode() {
		return code;
	}

	public SysRole setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public SysRole setName(String name) {
		this.name = name;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public SysRole setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public static final String ID = "id";

	public static final String VERSION = "version";

	public static final String CREATER_ID = "creater_id";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATER_ID = "updater_id";

	public static final String UPDATE_TIME = "update_time";

	public static final String STATUS = "status";

	public static final String CODE = "code";

	public static final String NAME = "name";

	public static final String REMARK = "remark";

}
