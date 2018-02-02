package me.zhoubl.zfinal.synthesis.service.impl;

import java.io.File;
import java.util.List;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Strings;
import me.zhoubl.zfinal.synthesis.api.CommonFilesService;
import me.zhoubl.zfinal.synthesis.entity.CommonFiles;
import me.zhoubl.zfinal.synthesis.ex.CommonFiilesBizEx;
import me.zhoubl.zfinal.synthesis.mapper.CommonFilesDao;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
@Service
public class CommonFilesServiceImpl extends ServiceImpl<CommonFilesDao, CommonFiles> implements CommonFilesService {

    @Override
    public void create(CommonFiles entity) throws CommonFiilesBizEx {
        insert(entity);
    }

    @Override
    public void delete(CommonFiles entity, String rootPath) throws CommonFiilesBizEx {
        entity = selectById(entity.getId());
        File file = new File(rootPath + entity.getFilePath());
        if (file.exists()) {
            file.delete();
        }
        deleteById(entity);
    }

    @Override
    public List<CommonFiles> getAllByUUID(String uuid, String param) throws CommonFiilesBizEx {
        if (Strings.isNullOrEmpty(param)) {
            return selectList(new EntityWrapper<CommonFiles>().eq("uuid", uuid).eq("status", 1).orderBy("create_time", false));
        } else {
            return selectList(new EntityWrapper<CommonFiles>().eq("uuid", uuid).eq("param", param).eq("status", 1).orderBy("create_time", false));
        }
    }

    @Override
    public CommonFiles getOneByUUID(String uuid, String param) throws CommonFiilesBizEx {
        if (Strings.isNullOrEmpty(param)) {
            return selectOne(new EntityWrapper<CommonFiles>().eq("uuid", uuid).eq("status", 1).orderBy("create_time", false));
        } else {
            return selectOne(new EntityWrapper<CommonFiles>().eq("uuid", uuid).eq("param", param).eq("status", 1).orderBy("create_time", false));
        }
    }
}
