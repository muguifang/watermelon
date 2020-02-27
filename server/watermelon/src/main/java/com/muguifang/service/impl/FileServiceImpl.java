package com.muguifang.service.impl;

import com.muguifang.common.exception.exceptions.ParamException;
import com.muguifang.common.utils.Base64Util;
import com.muguifang.common.utils.FileUtil;
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

import java.io.*;
import java.nio.channels.FileChannel;
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
        tLbt.setPic("D:/img/" + path);
        tLbt.setInsertdate(new Date());
        tLbtMapper.insert(tLbt);
    }

    @Override
    public List<Map<String, String>> getAllPhoto() {
        TLbtExample example = new TLbtExample();
        example.setOrderByClause("insertDate DESC");
        List<TLbt> tLbts = tLbtMapper.selectByExample(example);
        List<Map<String, String>> result = new ArrayList<>();
        for (TLbt tLbt : tLbts) {
            Map<String, String> map = new HashMap<>();
            String photo = Base64Util.base64Convert(tLbt.getPic());
            tLbt.setPic(photo);
            map.put("id", tLbt.getId().toString());
            map.put("pic", "data:image/png;base64," + photo);
            result.add(map);
        }
        return result;
    }

    @Override
    public void deleteFile(List<Integer> ids) {
        TLbtExample example = new TLbtExample();
        TLbtExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        List<TLbt> tLbts = tLbtMapper.selectByExample(example);
        for (TLbt tLbt : tLbts) {
            tLbtMapper.deleteByPrimaryKey(tLbt.getId());
            String path = tLbt.getPic();
            FileUtil.deleteFile(path);
//            if (path == null || "".equals(path)) {
//                throw new ParamException(501, "文件路径无效");
//            }
//            File file = new File(path);
//            if (file.isFile()) {// 表示该文件不是文件夹
//                file.delete();
//            }
        }
    }

    @Override
    public void updateFilePath(Map<String, Object> param) {
        if (param == null) {
            throw new ParamException(501, "图片信息获取异常");
        }
        Integer id = Integer.parseInt((String) param.get("id"));
        String path = (String) param.get("name");
        if (id == null || path == null || "".equals(path)) {
            throw new ParamException(501, "图片信息获取异常");
        }
        TLbt tLbt = tLbtMapper.selectByPrimaryKey(id);
        tLbt.setPic("D:/img/" + path);
        tLbtMapper.updateByPrimaryKeySelective(tLbt);
    }

    @Override
    public void deleteServerFile(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            throw new ParamException(501, "获取文件名称失败");
        }
        File file = new File("D:/img/" + fileName);
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
        } else {
            // 首先得到当前的路径
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
                deleteServerFile(fileName);
            }
            file.delete();
        }
    }

}
