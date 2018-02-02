package me.zhoubl.zfinal.synthesis.entity;

import java.math.BigDecimal;
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
 * @since 2017-06-08
 */
@TableName("common_address")
public class CommonAddress extends BaseEntity<CommonAddress> {

	public CommonAddress(){}
	public CommonAddress(Long id){
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
	 * 类型
	 */
	private Integer type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 纬度
	 */
	private BigDecimal lat;
	/**
	 * 经度
	 */
	private BigDecimal lng;


	public Long getId() {
		return id;
	}

	public CommonAddress setId(Long id) {
		this.id = id;
		return this;
	}

	public Integer getVersion() {
		return version;
	}

	public CommonAddress setVersion(Integer version) {
		this.version = version;
		return this;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public CommonAddress setCreaterId(Long createrId) {
		this.createrId = createrId;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public CommonAddress setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public CommonAddress setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public CommonAddress setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public CommonAddress setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Integer getType() {
		return type;
	}

	public CommonAddress setType(Integer type) {
		this.type = type;
		return this;
	}

	public String getName() {
		return name;
	}

	public CommonAddress setName(String name) {
		this.name = name;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public CommonAddress setAddress(String address) {
		this.address = address;
		return this;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public CommonAddress setLat(BigDecimal lat) {
		this.lat = lat;
		return this;
	}

	public BigDecimal getLng() {
		return lng;
	}

	public CommonAddress setLng(BigDecimal lng) {
		this.lng = lng;
		return this;
	}

	public static final String ID = "id";

	public static final String VERSION = "version";

	public static final String CREATER_ID = "creater_id";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATER_ID = "updater_id";

	public static final String UPDATE_TIME = "update_time";

	public static final String STATUS = "status";

	public static final String TYPE = "type";

	public static final String NAME = "name";

	public static final String ADDRESS = "address";

	public static final String LAT = "lat";

	public static final String LNG = "lng";


}
