package me.zhoubl.zfinal.synthesis.api;


import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import me.zhoubl.zfinal.synthesis.ex.SysUserBizEx;

/**
 * <p>
 * 系统用户api
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
public interface SysUserService extends IService<SysUser> {
	public SysUser create(SysUser entity, String roleIds) throws SysUserBizEx;
	public void delete(SysUser entity) throws SysUserBizEx;
	public SysUser update(SysUser entity, String roleIds) throws SysUserBizEx;
	public void updateStatus(SysUser entity, Integer status) throws SysUserBizEx;
	public SysUser getByCode(String code) throws SysUserBizEx;
	public void updatePwd(SysUser entity, String pwd, String nPwd) throws SysUserBizEx;
}
