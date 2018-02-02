package me.zhoubl.zfinal.synthesis.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.common.utils.UtilBean;
import me.zhoubl.zfinal.synthesis.api.CommonVersionService;
import me.zhoubl.zfinal.synthesis.entity.CommonVersion;
import me.zhoubl.zfinal.synthesis.ex.CommonVersionBizEx;
import me.zhoubl.zfinal.synthesis.mapper.CommonVersionDao;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-07-21
 */
@Service
public class CommonVersionServiceImpl extends ServiceImpl<CommonVersionDao, CommonVersion>
		implements CommonVersionService {

	@Override
	public void create(CommonVersion entity) throws CommonVersionBizEx {
		insert(entity);
	}

	@Override
	public void delete(CommonVersion entity) throws CommonVersionBizEx {
		deleteById(entity.getId());
	}

	@Override
	public void update(CommonVersion entity) throws CommonVersionBizEx {
		CommonVersion e = selectById(entity.getId());
		UtilBean.copyPropertiesIgnoreNull(entity, e);
		updateById(e);

	}

	@Override
	public void updateStatus(CommonVersion entity, Integer status) throws CommonVersionBizEx {
		entity = selectById(entity.getId());
		entity.setStatus(status);
		updateById(entity);
	}

	@Override
	public CommonVersion getNewVersionByType(Integer type) throws CommonVersionBizEx {
		List<CommonVersion> list = selectList(
				new EntityWrapper<CommonVersion>().eq("type", type).eq("status", 1).orderBy("create_time desc"));
		
		if (list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}

}
