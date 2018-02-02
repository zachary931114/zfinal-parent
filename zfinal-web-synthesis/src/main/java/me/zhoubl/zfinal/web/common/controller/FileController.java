package me.zhoubl.zfinal.web.common.controller;

import com.baomidou.mybatisplus.toolkit.Sequence;
import me.zhoubl.zfinal.common.web.dto.ReturnJson;
import me.zhoubl.zfinal.synthesis.api.CommonFilesService;
import me.zhoubl.zfinal.synthesis.entity.CommonFiles;
import me.zhoubl.zfinal.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Created by zhoubl on 2017/5/23.
 */
@Controller
@RequestMapping("/admin/file")
public class FileController extends BaseController {

    @Autowired
    private CommonFilesService commonFilesService;

    @RequestMapping(value = "/save")
    @ResponseBody
    public ReturnJson save(@RequestParam(value = "files", required = true) MultipartFile[] files, @RequestParam(value = "uuid", required = true) String uuid, @RequestParam(value = "filePathPrefix") String filePathPrefix, @RequestParam(value = "param") String param) throws Exception {
        String filePath = getFilesPath() + File.separator + filePathPrefix + File.separator + uuid;
        Sequence sequence = new Sequence();

        for (MultipartFile mf : files) {
            String fileName = sequence.nextId()
                    + mf.getOriginalFilename().substring(
                    mf.getOriginalFilename().lastIndexOf("."),
                    mf.getOriginalFilename().length());
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }
            f = new File(filePath + File.separator + fileName);
            mf.transferTo(f);
            CommonFiles commonFiles = new CommonFiles();
            commonFiles.setUuid(uuid);
            commonFiles.setFileName(fileName);
            commonFiles.setOldFileName(mf.getOriginalFilename());
            commonFiles.setParam(param);
            commonFiles.setFileSize(mf.getSize());
            commonFiles.setFilePath(File.separator + filePathPrefix + File.separator + uuid + File.separator + fileName);
            commonFiles.setFileSuffix(mf.getOriginalFilename().substring(
                    mf.getOriginalFilename().lastIndexOf("."),
                    mf.getOriginalFilename().length()));
            commonFilesService.create(commonFiles);
        }

        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ReturnJson delete(@RequestParam(value = "id", required = true) Long id) throws Exception {
        commonFilesService.delete(new CommonFiles(id),getFilesPath());
        return new ReturnJson(true, null, null);
    }

    @RequestMapping(value = "/getFileList")
    @ResponseBody
    public ReturnJson getFileList(@RequestParam(value = "uuid", required = true) String uuid, @RequestParam(value = "param", required = true) String param) throws Exception {
        List<CommonFiles> list = commonFilesService.getAllByUUID(uuid, param);
        return new ReturnJson(true, null, list);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downLoad(@RequestParam(value = "id", required = true) Long id)
            throws Exception {
        String filePath = getFilesPath();
        CommonFiles commonFiles = commonFilesService.selectById(id);
        File f = new File(filePath + commonFiles.getFilePath());
        sendFile(f, null, commonFiles.getOldFileName());
    }

}
