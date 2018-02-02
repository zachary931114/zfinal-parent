package me.zhoubl.zfinal.common.web.redis;

import me.zhoubl.zfinal.common.utils.serialization.ProtostuffSerializer;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;


import net.jcip.annotations.ThreadSafe;

/**
 * Created by zhoubl on 2017/2/11.
 */
@ThreadSafe
public class ShiroSessionProtostuffRedisSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o != null){
        	ShiroSessionPack shiroSessionPack = new ShiroSessionPack();
        	SimpleSession simpleSession = (SimpleSession) o;
        	shiroSessionPack.setId((String) simpleSession.getId());
        	shiroSessionPack.setStartTimestamp(simpleSession.getStartTimestamp());
        	shiroSessionPack.setStopTimestamp(simpleSession.getStopTimestamp());
        	shiroSessionPack.setLastAccessTime(simpleSession.getLastAccessTime());
        	shiroSessionPack.setTimeout(simpleSession.getTimeout());
        	shiroSessionPack.setExpired(simpleSession.isExpired());
        	shiroSessionPack.setHost(simpleSession.getHost());
        	shiroSessionPack.setAttributes(simpleSession.getAttributes());
            return ProtostuffSerializer.serialize(shiroSessionPack);
        }else{
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null) {
			SimpleSession simpleSession = new SimpleSession();
        	ShiroSessionPack shiroSessionPack = ProtostuffSerializer.deserialize(bytes, ShiroSessionPack.class);
        	simpleSession.setId(shiroSessionPack.getId());
        	simpleSession.setStartTimestamp(shiroSessionPack.getStartTimestamp());
        	simpleSession.setStopTimestamp(shiroSessionPack.getStopTimestamp());
        	simpleSession.setLastAccessTime(shiroSessionPack.getLastAccessTime());
        	simpleSession.setTimeout(shiroSessionPack.getTimeout());
        	simpleSession.setExpired(shiroSessionPack.isExpired());
        	simpleSession.setHost(shiroSessionPack.getHost());
        	simpleSession.setAttributes(shiroSessionPack.getAttributes());
            return simpleSession;
        } else {
            return null;
        }
    }
}
