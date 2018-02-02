package me.zhoubl.zfinal.synthesis.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.common.utils.UtilBean;
import me.zhoubl.zfinal.synthesis.api.SysPermissionService;
import me.zhoubl.zfinal.synthesis.entity.SysPermission;
import me.zhoubl.zfinal.synthesis.ex.SysPermissionBizEx;
import me.zhoubl.zfinal.synthesis.mapper.SysPermissionDao;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermission> implements SysPermissionService {
    @Override
    public void create(SysPermission entity) throws SysPermissionBizEx {
        if (selectCount(new EntityWrapper<SysPermission>().eq("code", entity.getCode())) > 0) {
            throw new SysPermissionBizEx(SysPermissionBizEx.BIZ_ERROR_CODE, "编码已存在");
        }
        insert(entity);
    }

    @Override
    public void delete(SysPermission entity) throws SysPermissionBizEx {
        deleteById(entity.getId());
    }

    @Override
    public void update(SysPermission entity) throws SysPermissionBizEx {
        if (selectCount(new EntityWrapper<SysPermission>().eq("code", entity.getCode()).ne("id", entity.getId())) > 0) {
            throw new SysPermissionBizEx(SysPermissionBizEx.BIZ_ERROR_CODE, "编码已存在");
        }
        SysPermission e = selectById(entity.getId());
        UtilBean.copyPropertiesIgnoreNull(entity, e);
        updateById(e);
    }

    @Override
    public void updateStatus(SysPermission entity, Integer status) throws SysPermissionBizEx {
        entity = selectById(entity.getId());
        entity.setStatus(status);
        updateById(entity);
    }

    @Override
    public int getCountByCode(String code) throws SysPermissionBizEx {
        return selectCount(new EntityWrapper<SysPermission>().eq("code", code).eq("status", "1"));
    }
}
