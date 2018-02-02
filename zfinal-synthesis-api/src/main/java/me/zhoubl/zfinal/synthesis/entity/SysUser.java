package me.zhoubl.zfinal.synthesis.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-18
 */
@TableName("sys_user")
public class SysUser extends BaseEntity<SysUser> {

	public SysUser() {
	}

	public SysUser(Long id) {
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
	 * 用户名
	 */
	private String code;
	/**
	 * 昵称
	 */
	private String name;
	private String pwd;
	private String email;
	private String phone;
	private String city;
	@TableField("file_uuid")
	private String fileUuid;

	@TableField(exist = false)
	private String headPortraitFilePath;

	public Long getId() {
		return id;
	}

	public SysUser setId(Long id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public SysUser setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public SysUser setCreaterId(Long createrId) {
		this.createrId = createrId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public SysUser setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public SysUser setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public SysUser setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public SysUser setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getCode() {
		return code;
	}

	public SysUser setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public SysUser setName(String name) {
		this.name = name;
		return this;
	}

	public String getPwd() {
		return pwd;
	}

	public SysUser setPwd(String pwd) {
		this.pwd = pwd;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public SysUser setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public SysUser setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public SysUser setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
		return this;
	}

	public String getHeadPortraitFilePath() {
		return headPortraitFilePath;
	}

	public void setHeadPortraitFilePath(String headPortraitFilePath) {
		this.headPortraitFilePath = headPortraitFilePath;
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

	public static final String PWD = "pwd";

	public static final String EMAIL = "email";

	public static final String PHONE = "phone";
	
	public static final String CITY = "city";

	public static final String FILE_UUID = "file_uuid";

}
