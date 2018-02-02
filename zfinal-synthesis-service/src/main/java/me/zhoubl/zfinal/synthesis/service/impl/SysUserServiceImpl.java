package me.zhoubl.zfinal.synthesis.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.common.utils.UtilAES;
import me.zhoubl.zfinal.common.utils.UtilBean;
import me.zhoubl.zfinal.common.utils.UtilCodec;
import me.zhoubl.zfinal.synthesis.api.SysRoleService;
import me.zhoubl.zfinal.synthesis.api.SysUserRoleService;
import me.zhoubl.zfinal.synthesis.api.SysUserService;
import me.zhoubl.zfinal.synthesis.entity.SysRole;
import me.zhoubl.zfinal.synthesis.entity.SysUser;
import me.zhoubl.zfinal.synthesis.entity.SysUserRole;
import me.zhoubl.zfinal.synthesis.ex.SysUserBizEx;
import me.zhoubl.zfinal.synthesis.mapper.SysUserDao;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public SysUser create(SysUser entity, String roleIds) throws SysUserBizEx {
        if (selectCount(new EntityWrapper<SysUser>().eq("code", entity.getCode())) > 0) {
            throw new SysUserBizEx(SysUserBizEx.BIZ_ERROR_CODE, "用户名已存在");
        }
        String codecPwd = "";
        try {
        	codecPwd = UtilCodec.pwdCodec(UtilAES.AesCbcDecode(Hex.decodeHex(String.valueOf(entity.getPwd()).toCharArray()), UtilAES.restoreSecretKey(UtilAES.KEY.getBytes()), UtilAES.IV.getBytes()), entity.getCode());
		} catch (DecoderException e) {
			throw new SysUserBizEx(SysUserBizEx.BIZ_ERROR_CODE,"密码加密错误");
		}
        entity.setPwd(codecPwd);
        insert(entity);
        updateItems(entity, roleIds);
        return entity;
    }

    @Override
    public void delete(SysUser entity) throws SysUserBizEx {
        deleteById(entity.getId());
    }

    @Override
    public SysUser update(SysUser entity, String roleIds) throws SysUserBizEx {
        if (selectCount(new EntityWrapper<SysUser>().eq("code", entity.getCode()).ne("id", entity.getId())) > 0) {
            throw new SysUserBizEx(SysUserBizEx.BIZ_ERROR_CODE, "用户名已存在");
        }
        if (!Strings.isNullOrEmpty(entity.getPwd())) {
        	String codecPwd = "";
            try {
            	codecPwd = UtilCodec.pwdCodec(UtilAES.AesCbcDecode(Hex.decodeHex(String.valueOf(entity.getPwd()).toCharArray()), UtilAES.restoreSecretKey(UtilAES.KEY.getBytes()), UtilAES.IV.getBytes()), entity.getCode());
    		} catch (DecoderException e) {
    			throw new SysUserBizEx(SysUserBizEx.BIZ_ERROR_CODE,"密码加密错误");
    		}
            entity.setPwd(codecPwd);
        } else {
            entity.setPwd(null);
        }
        SysUser e = selectById(entity.getId());
        UtilBean.copyPropertiesIgnoreNull(entity, e);
        updateById(e);
        updateItems(entity, roleIds);
        return e;
    }

    private void updateItems(SysUser entity, String idsStr) {
        if (!Strings.isNullOrEmpty(idsStr)) {
            sysUserRoleService.deleteByRelevanceId(entity.getId());
            String[] ids = idsStr.split(",");
            for (String id : ids) {
                if (!Strings.isNullOrEmpty(id)) {
                    SysUserRole target = new SysUserRole();
                    target.setSysUserId(entity.getId());
                    target.setSysRoleId(Long.valueOf(id));

                    SysRole role = sysRoleService.selectById(target.getSysRoleId());
                    target.setSysRoleCode(role.getCode());
                    target.setSysRoleName(role.getName());
                    sysUserRoleService.create(target);
                }
            }

        }
    }

    @Override
    public void updateStatus(SysUser entity, Integer status) throws SysUserBizEx {
        entity = selectById(entity.getId());
        entity.setStatus(status);
        updateById(entity);
    }

    @Override
    public SysUser getByCode(String code) throws SysUserBizEx {
        return selectOne(new EntityWrapper<SysUser>().eq("code", code));
    }

	@Override
	public void updatePwd(SysUser entity, String pwd, String nPwd) throws SysUserBizEx {
		entity = selectById(entity.getId());
		String codecPwd = "";
		String codecNPwd = "";
		try {
			codecPwd = UtilCodec.pwdCodec(UtilAES.AesCbcDecode(Hex.decodeHex(String.valueOf(pwd).toCharArray()), UtilAES.restoreSecretKey(UtilAES.KEY.getBytes()), UtilAES.IV.getBytes()), entity.getCode());
			codecNPwd = UtilCodec.pwdCodec(UtilAES.AesCbcDecode(Hex.decodeHex(String.valueOf(nPwd).toCharArray()), UtilAES.restoreSecretKey(UtilAES.KEY.getBytes()), UtilAES.IV.getBytes()), entity.getCode());
		} catch (DecoderException e) {
			throw new SysUserBizEx(SysUserBizEx.BIZ_ERROR_CODE,"密码加密错误");
		}
		if (entity.getPwd().equals(codecPwd)) {
			entity.setPwd(codecNPwd);
			updateById(entity);
		}else{
			throw new SysUserBizEx(SysUserBizEx.BIZ_ERROR_CODE,"原密码错误");
		}
	}
}
