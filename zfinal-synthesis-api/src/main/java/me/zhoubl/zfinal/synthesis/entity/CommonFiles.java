package me.zhoubl.zfinal.synthesis.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 	文件管理
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
@TableName("common_files")
public class CommonFiles extends BaseEntity<CommonFiles> {

	public CommonFiles(){}
	public CommonFiles(Long id){
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
     * 参数
     */
	private String param;
    /**
     * uuid
     */
	private String uuid;
    /**
     * 文件名称
     */
	@TableField("file_name")
	private String fileName;
    /**
     * 原文件名称
     */
	@TableField("old_file_name")
	private String oldFileName;
    /**
     * 文件大小
     */
	@TableField("file_size")
	private Long fileSize;
    /**
     * 文件路径
     */
	@TableField("file_path")
	private String filePath;
    /**
     * 文件后缀
     */
	@TableField("file_suffix")
	private String fileSuffix;


	public Long getId() {
		return id;
	}

	public CommonFiles setId(Long id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public CommonFiles setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public CommonFiles setCreaterId(Long createrId) {
		this.createrId = createrId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public CommonFiles setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public CommonFiles setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public CommonFiles setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public CommonFiles setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public String getParam() {
		return param;
	}

	public CommonFiles setParam(String param) {
		this.param = param;
		return this;
	}

	public String getUuid() {
		return uuid;
	}

	public CommonFiles setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	public String getFileName() {
		return fileName;
	}

	public CommonFiles setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public String getOldFileName() {
		return oldFileName;
	}

	public CommonFiles setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
		return this;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public CommonFiles setFileSize(Long fileSize) {
		this.fileSize = fileSize;
		return this;
	}

	public String getFilePath() {
		return filePath;
	}

	public CommonFiles setFilePath(String filePath) {
		this.filePath = filePath;
		return this;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public CommonFiles setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
		return this;
	}

	public static final String ID = "id";

	public static final String VERSION = "version";

	public static final String CREATER_ID = "creater_id";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATER_ID = "updater_id";

	public static final String UPDATE_TIME = "update_time";

	public static final String STATUS = "status";

	public static final String PARAM = "param";

	public static final String UUID = "uuid";

	public static final String FILE_NAME = "file_name";

	public static final String OLD_FILE_NAME = "old_file_name";

	public static final String FILE_SIZE = "file_size";

	public static final String FILE_PATH = "file_path";

	public static final String FILE_SUFFIX = "file_suffix";


}
