package me.zhoubl.zfinal.common.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import me.zhoubl.zfinal.common.service.BaseService;

import java.io.Serializable;

/**
 * Created by zhoubl on 2017/4/21.
 */
public class BaseServiceImpl<Dao extends BaseMapper<Entity>, Entity extends Serializable>
		extends ServiceImpl<Dao, Entity> implements BaseService<Entity> {
}
