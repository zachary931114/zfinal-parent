package me.zhoubl.zfinal.synthesis.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 	系统角色权限
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-18
 */
@TableName("sys_role_permission")
public class SysRolePermission extends BaseEntity<SysRolePermission> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 角色id
     */
	@TableField("sys_role_id")
	private Long sysRoleId;
    /**
     * 权限id
     */
	@TableField("sys_permission_id")
	private Long sysPermissionId;
    /**
     * 权限编码
     */
	@TableField("sys_permission_code")
	private String sysPermissionCode;
    /**
     * 权限名称
     */
	@TableField("sys_permission_name")
	private String sysPermissionName;


	public Long getId() {
		return id;
	}

	public SysRolePermission setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSysRoleId() {
		return sysRoleId;
	}

	public SysRolePermission setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
		return this;
	}

	public Long getSysPermissionId() {
		return sysPermissionId;
	}

	public SysRolePermission setSysPermissionId(Long sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
		return this;
	}

	public String getSysPermissionCode() {
		return sysPermissionCode;
	}

	public SysRolePermission setSysPermissionCode(String sysPermissionCode) {
		this.sysPermissionCode = sysPermissionCode;
		return this;
	}

	public String getSysPermissionName() {
		return sysPermissionName;
	}

	public SysRolePermission setSysPermissionName(String sysPermissionName) {
		this.sysPermissionName = sysPermissionName;
		return this;
	}

	public static final String ID = "id";

	public static final String SYS_ROLE_ID = "sys_role_id";

	public static final String SYS_PERMISSION_ID = "sys_permission_id";

	public static final String SYS_PERMISSION_CODE = "sys_permission_code";

	public static final String SYS_PERMISSION_NAME = "sys_permission_name";


}
