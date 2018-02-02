package me.zhoubl.zfinal.synthesis.api;


import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.CommonAddress;
import me.zhoubl.zfinal.synthesis.ex.CommonAddressBizEx;

/**
 * <p>
 *  公共地理地址api
 * </p>
 *
 * @author zhoubl
 * @since 2017-06-08
 */
public interface CommonAddressService extends IService<CommonAddress> {
	public void create(CommonAddress entity) throws CommonAddressBizEx;
	public void delete(CommonAddress entity) throws CommonAddressBizEx;
	public void update(CommonAddress entity) throws CommonAddressBizEx;
	public void updateStatus(CommonAddress entity, Integer status) throws CommonAddressBizEx;
}
