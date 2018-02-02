package me.zhoubl.zfinal.common.utils.serialization;

import java.io.Serializable;

/**
 * Protostuff包装类
 * Created by zhoubl on 2017/2/12.
 */
public class ProtostuffPack implements Serializable {

    public ProtostuffPack(){}

    public ProtostuffPack(Object object){
        this.object = object;
    }

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
