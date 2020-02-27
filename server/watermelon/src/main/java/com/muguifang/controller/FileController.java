package com.muguifang.controller;

import com.muguifang.common.utils.Base64Util;
import com.muguifang.result.ResultVo;
import com.muguifang.service.FileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description muguifang
 * @Date 2020/2/17 12:33
 * @Description 文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param lbt
     * @param path
     * @return
     */
    @PostMapping("/upload")
    public ResultVo uploadFile(@Param("lbt") MultipartFile lbt, @Param("path") String path){
        Map<String, Object> param = new HashMap<>();
        param.put("multipartFile", lbt);
        param.put("path", path);
        ResultVo resultVo = fileService.uploadFile(param);
        return resultVo;
    }

    /**
     * 文件路径存入DB
     * @param param
     * @return
     */
    @PostMapping("/addPhoto")
    public ResultVo addPhoto(@RequestBody Map<String, String> param){
        String path = param.get("path");
        fileService.addLbt(path);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 从磁盘删除指定文件
     * @param ids
     * @return
     */
    @DeleteMapping("/deletePhoto")
    public ResultVo deletePhoto(@RequestBody List<Integer> ids){
        fileService.deleteFile(ids);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 获取所有图片
     * @return
     */
    @GetMapping("/getAllPhoto")
    public ResultVo getAllPhoto(){
        List<Map<String, String>> allPhoto = fileService.getAllPhoto();
        return ResultVo.sendResult(200, "success", allPhoto);
    }

    /**
     * 修改图片信息
     * @param param
     * @return
     */
    @PutMapping("/updateFile")
    public ResultVo updateFilePath(@RequestBody Map<String, Object> param){
        fileService.updateFilePath(param);
        return ResultVo.sendResult(200, "success");
    }

    /**
     * 上传取消后删除服务器文件
     * @param fileName
     * @return
     */
    @DeleteMapping("/deleteServerFile")
    public ResultVo deleteServerFile(String fileName){
        fileService.deleteServerFile(fileName);
        return ResultVo.sendResult(200, "success");
    }

}
