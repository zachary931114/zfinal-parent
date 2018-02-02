package me.zhoubl.zfinal.synthesis.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 系统消息
 * </p>
 *
 * @author zhoubl
 * @since 2017-06-18
 */
@TableName("sys_message")
public class SysMessage extends BaseEntity<SysMessage> {

    public SysMessage(){}
    public SysMessage(Long id){
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
     * 用户id
     */
    @TableField("sys_user_id")
    private Long sysUserId;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 地址
     */
    private String url;
    /**
     * 参数
     */
    private String params;


    public Long getId() {
        return id;
    }

    public SysMessage setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public SysMessage setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public SysMessage setCreaterId(Long createrId) {
        this.createrId = createrId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public SysMessage setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public SysMessage setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public SysMessage setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public SysMessage setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public SysMessage setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public SysMessage setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SysMessage setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SysMessage setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getParams() {
        return params;
    }

    public SysMessage setParams(String params) {
        this.params = params;
        return this;
    }

    public static final String ID = "id";

    public static final String VERSION = "version";

    public static final String CREATER_ID = "creater_id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATER_ID = "updater_id";

    public static final String UPDATE_TIME = "update_time";

    public static final String STATUS = "status";

    public static final String SYS_USER_ID = "sys_user_id";

    public static final String TYPE = "type";

    public static final String TITLE = "title";

    public static final String URL = "url";

    public static final String PARAMS = "params";

}
