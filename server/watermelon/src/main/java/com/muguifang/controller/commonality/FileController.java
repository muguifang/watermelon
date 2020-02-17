package com.muguifang.controller.commonality;

import com.muguifang.result.ResultVo;
import com.muguifang.service.FileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/17 12:33
 * @Description 文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResultVo uploadFile(@Param("lbt") MultipartFile lbt, @Param("path") String path){
        Map<String, Object> param = new HashMap<>();
        param.put("multipartFile", lbt);
        param.put("path", path);
        ResultVo resultVo = fileService.uploadFile(param);
        return resultVo;
    }
    @PostMapping("/addPhoto")
    public ResultVo addPhoto(@RequestBody Map<String, String> param){
        String path = param.get("path");
        fileService.addLbt(path);
        return ResultVo.sendResult(200, "success");
    }

    @DeleteMapping("/deletePhoto")
    public ResultVo deletePhoto(Integer id){
        fileService.deleteFile(id);
        return ResultVo.sendResult(200, "success");
    }

    @GetMapping("/getAllPhoto")
    public ResultVo getAllPhoto(){
        List<Map<Integer, String>> allPhoto = fileService.getAllPhoto();
        return ResultVo.sendResult(200, "success", allPhoto);
    }
}
