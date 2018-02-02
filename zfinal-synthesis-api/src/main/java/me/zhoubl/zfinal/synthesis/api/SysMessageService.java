package me.zhoubl.zfinal.synthesis.api;

import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.SysMessage;
import me.zhoubl.zfinal.synthesis.ex.SysMessageBizEx;

import java.util.List;


/**
 * <p>
 * 系统消息api
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
public interface SysMessageService extends IService<SysMessage> {
    public void create(SysMessage entity) throws SysMessageBizEx;
    public void delete(SysMessage entity) throws SysMessageBizEx;
    public void update(SysMessage entity) throws SysMessageBizEx;
    public void updateStatus(SysMessage entity, Integer status) throws SysMessageBizEx;
    public List<SysMessage> getListByUserId(Long sysUserId) throws SysMessageBizEx;
    public Integer getListSizeByUserId(Long sysUserId) throws SysMessageBizEx;
}
