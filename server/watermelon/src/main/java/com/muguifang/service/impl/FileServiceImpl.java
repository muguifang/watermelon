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
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
            return new ResultVo(200, "success", targetFile);
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
        tLbt.setPic(path);
        tLbtMapper.insert(tLbt);
    }

    @Override
    public List<Map<Integer, String>> getAllPhoto() {
        TLbtExample example = new TLbtExample();
        List<TLbt> tLbts = tLbtMapper.selectByExample(example);
        List<Map<Integer, String>> result = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();
        for(TLbt tLbt : tLbts){
            String path = tLbt.getPic();
            InputStream in = null;
            byte[] data = null;
            //读取图片字节数组
            try {
                in = new FileInputStream(path);
                data = new byte[in.available()];
                in.read(data);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(data);//返回Base64编码过的字节数组字符串
            map.put(tLbt.getId(),encode);
        }
        result.add(map);

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
