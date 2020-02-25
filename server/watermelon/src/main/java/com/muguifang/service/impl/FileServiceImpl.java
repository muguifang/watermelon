package com.muguifang.service.impl;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.mapper.TLbtMapper;
import com.muguifang.po.TLbt;
import com.muguifang.po.TLbtExample;
import com.muguifang.result.ResultVo;
import com.muguifang.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description qubaolai
 * @Date 2020/2/17 12:37
 * @Description 文件上传
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private TLbtMapper tLbtMapper;

    public ResultVo uploadFile(Map<String, Object> param) {
        MultipartFile multipartFile = (MultipartFile) param.get("multipartFile");
        //获取原始文件名称(包含格式)
        String originalFileName = multipartFile.getOriginalFilename();
        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //设置文件新名称
        //获取文件名称（不包含格式）
//        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + "." + type;
        //在指定路径下创建一个文件
        String path = (String) param.get("path");
        File filePath = new File(path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdir();
        }
        File targetFile = new File(path, fileName);
        //将文件保存到服务器指定位置
        try {
            multipartFile.transferTo(targetFile);
            //将文件在服务器的存储路径返回
            return new ResultVo(200, "success", fileName);
        } catch (IOException e) {
            log.error("上传文件发生异常:{}", e.getMessage(), e);
            e.printStackTrace();
            return new ResultVo(509, "上传失败");
        }
    }

    @Override
    public void addLbt(String path) {
        TLbt tLbt = new TLbt();
        tLbt.setId(0);
        tLbt.setPic("upload/"+path);
        tLbt.setInsertdate(new Date());
        tLbtMapper.insert(tLbt);
    }

    @Override
    public List<Map<String, String>> getAllPhoto() {
        TLbtExample example = new TLbtExample();
        example.setOrderByClause("insertDate DESC");
        List<TLbt> tLbts = tLbtMapper.selectByExample(example);
        List<Map<String, String>> result = new ArrayList<>();
        for(TLbt tLbt : tLbts){
            Map<String, String> map = new HashMap<>();
            String picStr = tLbt.getPic();
            String pic = picStr.substring(picStr.lastIndexOf("\\")+1,picStr.length());
//            map.put(tLbt.getId(),"@/assets/"+ pic);
            map.put("id",tLbt.getId().toString());
            map.put("pic",pic);
            result.add(map);
        }

        return result;
    }

    @Override
    public void deleteFile(Integer id) {
        TLbt tLbt = tLbtMapper.selectByPrimaryKey(id);
        tLbtMapper.deleteByPrimaryKey(id);
        String path = tLbt.getPic();
        if (path == null || "".equals(path)) {
            throw new ParamException(501, "文件路径无效");
        }
        File file = new File(path);
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
        }
    }
}
