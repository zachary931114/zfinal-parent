package me.zhoubl.zfinal.synthesis.api;

import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.SysUserRole;
import me.zhoubl.zfinal.synthesis.ex.SysUserRoleBizEx;

import java.util.List;


/**
 * <p>
 *  系统用户角色api
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    public void create(SysUserRole entity) throws SysUserRoleBizEx;
    public void deleteByRelevanceId(Long id) throws SysUserRoleBizEx;
    public List<SysUserRole> getAllByRelevanceId(Long id) throws SysUserRoleBizEx;
}
