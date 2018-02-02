package me.zhoubl.zfinal.synthesis.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubl
 * @since 2017-07-21
 */
@TableName("common_version")
public class CommonVersion extends BaseEntity<CommonVersion> {
	
	public CommonVersion(){}
	public CommonVersion(Long id){
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
	@TableField("file_uuid")
	private String fileUuid;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 版本号
	 */
	private String code;
	/**
	 * 描述
	 */
	private String remark;
	
	/**
	 * 下载路径
	 */
	@TableField(exist = false)
	private String downLoadPath;

	public Long getId() {
		return id;
	}

	public CommonVersion setId(Long id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public CommonVersion setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public CommonVersion setCreaterId(Long createrId) {
		this.createrId = createrId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public CommonVersion setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public CommonVersion setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public CommonVersion setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public CommonVersion setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public CommonVersion setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public CommonVersion setType(Integer type) {
		this.type = type;
		return this;
	}

	public String getCode() {
		return code;
	}

	public CommonVersion setCode(String code) {
		this.code = code;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public CommonVersion setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	
	public String getDownLoadPath() {
		return downLoadPath;
	}
	
	public void setDownLoadPath(String downLoadPath) {
		this.downLoadPath = downLoadPath;
	}

	public static final String ID = "id";

	public static final String VERSION = "version";

	public static final String CREATER_ID = "creater_id";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATER_ID = "updater_id";

	public static final String UPDATE_TIME = "update_time";

	public static final String STATUS = "status";

	public static final String FILE_UUID = "file_uuid";

	public static final String TYPE = "type";

	public static final String CODE = "code";

	public static final String REMARK = "remark";


}
