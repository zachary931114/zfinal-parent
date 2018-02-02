package me.zhoubl.zfinal.common.service;

import java.io.Serializable;

import com.baomidou.mybatisplus.service.IService;

/**
 * Created by zhoubl on 2017/4/21.
 */
public interface BaseService<Entity extends Serializable> extends IService<Entity> {
}
