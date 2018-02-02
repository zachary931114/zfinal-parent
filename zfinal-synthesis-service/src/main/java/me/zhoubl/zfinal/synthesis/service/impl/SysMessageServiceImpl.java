package me.zhoubl.zfinal.synthesis.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.synthesis.api.SysMessageService;
import me.zhoubl.zfinal.synthesis.entity.SysMessage;
import me.zhoubl.zfinal.synthesis.ex.SysMessageBizEx;
import me.zhoubl.zfinal.synthesis.mapper.SysMessageDao;
import org.springframework.stereotype.Service;


/**
 * Created by zhoubl on 2017/6/18.
 */
@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageDao, SysMessage> implements SysMessageService {

    @Override
    public void create(SysMessage entity) throws SysMessageBizEx {
        insert(entity);
    }

    @Override
    public void delete(SysMessage entity) throws SysMessageBizEx {
        deleteById(entity.getId());
    }

    @Override
    public void update(SysMessage entity) throws SysMessageBizEx {

    }

    @Override
    public void updateStatus(SysMessage entity, Integer status) throws SysMessageBizEx {

    }

    @Override
    public List<SysMessage> getListByUserId(Long sysUserId) throws SysMessageBizEx {
        return selectPage(new Page<SysMessage>(1, 20), new EntityWrapper<SysMessage>().eq("status", 1).eq("sys_user_id", sysUserId).orderBy("create_time", false)).getRecords();
    }

    @Override
    public Integer getListSizeByUserId(Long sysUserId) throws SysMessageBizEx {
        return selectCount(new EntityWrapper<SysMessage>().eq("status", 1).eq("sys_user_id", sysUserId));
    }
}
