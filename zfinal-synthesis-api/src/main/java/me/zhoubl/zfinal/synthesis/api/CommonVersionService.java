package me.zhoubl.zfinal.synthesis.api;


import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.CommonVersion;
import me.zhoubl.zfinal.synthesis.ex.CommonVersionBizEx;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubl
 * @since 2017-07-21
 */
public interface CommonVersionService extends IService<CommonVersion> {
	public void create(CommonVersion entity) throws CommonVersionBizEx;
	public void delete(CommonVersion entity) throws CommonVersionBizEx;
	public void update(CommonVersion entity) throws CommonVersionBizEx;
	public void updateStatus(CommonVersion entity, Integer status) throws CommonVersionBizEx;
	public CommonVersion getNewVersionByType(Integer type) throws CommonVersionBizEx;;
}
