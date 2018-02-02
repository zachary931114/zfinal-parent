package me.zhoubl.zfinal.synthesis.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 	系统用户角色
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity<SysUserRole> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 用户id
     */
	@TableField("sys_user_id")
	private Long sysUserId;
    /**
     * 角色id
     */
	@TableField("sys_role_id")
	private Long sysRoleId;
    /**
     * 角色编码
     */
	@TableField("sys_role_code")
	private String sysRoleCode;
    /**
     * 角色名称
     */
	@TableField("sys_role_name")
	private String sysRoleName;


	public Long getId() {
		return id;
	}

	public SysUserRole setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSysUserId() {
		return sysUserId;
	}

	public SysUserRole setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
		return this;
	}

	public Long getSysRoleId() {
		return sysRoleId;
	}

	public SysUserRole setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
		return this;
	}

	public String getSysRoleCode() {
		return sysRoleCode;
	}

	public SysUserRole setSysRoleCode(String sysRoleCode) {
		this.sysRoleCode = sysRoleCode;
		return this;
	}

	public String getSysRoleName() {
		return sysRoleName;
	}

	public SysUserRole setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
		return this;
	}

	public static final String ID = "id";

	public static final String SYS_USER_ID = "sys_user_id";

	public static final String SYS_ROLE_ID = "sys_role_id";

	public static final String SYS_ROLE_CODE = "sys_role_code";

	public static final String SYS_ROLE_NAME = "sys_role_name";


}
