package me.zhoubl.zfinal.synthesis.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import me.zhoubl.zfinal.common.entity.BaseEntity;

/**
 * <p>
 * 	系统角色菜单
 * </p>
 *
 * @author zhoubl
 * @since 2017-06-24
 */
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 角色id
     */
	@TableField("sys_role_id")
	private Long sysRoleId;
    /**
     * 菜单id
     */
	@TableField("sys_menu_id")
	private Long sysMenuId;
    /**
     * 菜单编码
     */
	@TableField("sys_menu_code")
	private String sysMenuCode;
    /**
     * 菜单名称
     */
	@TableField("sys_menu_name")
	private String sysMenuName;


	public Long getId() {
		return id;
	}

	public SysRoleMenu setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSysRoleId() {
		return sysRoleId;
	}

	public SysRoleMenu setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
		return this;
	}

	public Long getSysMenuId() {
		return sysMenuId;
	}

	public SysRoleMenu setSysMenuId(Long sysMenuId) {
		this.sysMenuId = sysMenuId;
		return this;
	}

	public String getSysMenuCode() {
		return sysMenuCode;
	}

	public SysRoleMenu setSysMenuCode(String sysMenuCode) {
		this.sysMenuCode = sysMenuCode;
		return this;
	}

	public String getSysMenuName() {
		return sysMenuName;
	}

	public SysRoleMenu setSysMenuName(String sysMenuName) {
		this.sysMenuName = sysMenuName;
		return this;
	}

	public static final String ID = "id";

	public static final String SYS_ROLE_ID = "sys_role_id";

	public static final String SYS_MENU_ID = "sys_menu_id";

	public static final String SYS_MENU_CODE = "sys_menu_code";

	public static final String SYS_MENU_NAME = "sys_menu_name";


}
