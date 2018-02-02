package me.zhoubl.zfinal.synthesis.api;


import com.baomidou.mybatisplus.service.IService;
import me.zhoubl.zfinal.synthesis.entity.CommonFiles;
import me.zhoubl.zfinal.synthesis.ex.CommonFiilesBizEx;

import java.util.List;

/**
 * <p>
 * 文件管理api
 * </p>
 *
 * @author zhoubl
 * @since 2017-05-17
 */
public interface CommonFilesService extends IService<CommonFiles> {
    public void create(CommonFiles entity) throws CommonFiilesBizEx;
    public void delete(CommonFiles entity, String rootPath) throws CommonFiilesBizEx;
    public List<CommonFiles> getAllByUUID(String uuid, String param) throws CommonFiilesBizEx;
    public CommonFiles getOneByUUID(String uuid, String param) throws CommonFiilesBizEx;
}
