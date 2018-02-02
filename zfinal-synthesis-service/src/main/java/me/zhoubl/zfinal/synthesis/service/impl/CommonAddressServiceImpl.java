package me.zhoubl.zfinal.synthesis.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.common.utils.UtilBaiduMap;
import me.zhoubl.zfinal.common.utils.UtilBean;
import me.zhoubl.zfinal.synthesis.api.CommonAddressService;
import me.zhoubl.zfinal.synthesis.entity.CommonAddress;
import me.zhoubl.zfinal.synthesis.ex.CommonAddressBizEx;
import me.zhoubl.zfinal.synthesis.mapper.CommonAddressDao;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-06-08
 */
@Service
public class CommonAddressServiceImpl extends ServiceImpl<CommonAddressDao, CommonAddress> implements CommonAddressService {

    @Override
    public void create(CommonAddress entity) throws CommonAddressBizEx {
        Map<String, BigDecimal> latLngMap = null;
        try {
            latLngMap = UtilBaiduMap.getLatAndLngByAddress(entity.getAddress());
        } catch (Exception e) {
            throw new CommonAddressBizEx(CommonAddressBizEx.BIZ_ERROR_CODE,"获取经纬度失败");
        }
        if (latLngMap != null){
            entity.setLat(latLngMap.get("lat"));
            entity.setLng(latLngMap.get("lng"));
        }
        insert(entity);

    }

    @Override
    public void delete(CommonAddress entity) throws CommonAddressBizEx {
        deleteById(entity.getId());
    }

    @Override
    public void update(CommonAddress entity) throws CommonAddressBizEx {
        Map<String, BigDecimal> latLngMap = null;
        try {
            latLngMap = UtilBaiduMap.getLatAndLngByAddress(entity.getAddress());
        } catch (Exception e) {
            throw new CommonAddressBizEx(CommonAddressBizEx.BIZ_ERROR_CODE,"获取经纬度失败");
        }
        if (latLngMap != null){
            entity.setLat(latLngMap.get("lat"));
            entity.setLng(latLngMap.get("lng"));
        }
        CommonAddress e = selectById(entity.getId());
        UtilBean.copyPropertiesIgnoreNull(entity,e);
        updateById(e);
    }

    @Override
    public void updateStatus(CommonAddress entity, Integer status) throws CommonAddressBizEx {
        entity = selectById(entity.getId());
        entity.setStatus(status);
        updateById(entity);
    }
}
