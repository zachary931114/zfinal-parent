package me.zhoubl.zfinal.common.utils.serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Protostuff 工具类
 * Created by zhoubl on 2017/2/12.
 */
@ThreadSafe
public class ProtostuffSerializer{

    private static Map<Class, Schema> schemaCache = new ConcurrentHashMap<>();

    public static <T> byte[] serialize(T obj) {
        Class<T> klass = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        if (schemaCache.containsKey(klass)) {
            return ProtostuffIOUtil.toByteArray(obj, schemaCache.get(klass), buffer);
        } else {
            schemaCache.put(klass, RuntimeSchema.getSchema(klass));
            return ProtostuffIOUtil.toByteArray(obj, schemaCache.get(klass), buffer);
        }
    }

    public static <T> T deserialize(byte[] bs, Class<T> klass) {
        if (schemaCache.containsKey(klass)) {
            Schema<T> schema = schemaCache.get(klass);
            T msg = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bs, msg, schema);
            return msg;
        } else {
            Schema<T> schema = RuntimeSchema.getSchema(klass);
            T msg = schema.newMessage();
            schemaCache.put(klass, schema);
            ProtostuffIOUtil.mergeFrom(bs, msg, schema);
            return msg;
        }
    }
}
