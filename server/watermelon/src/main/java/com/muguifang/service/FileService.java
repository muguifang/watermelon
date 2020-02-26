package com.muguifang.service;

import com.muguifang.result.ResultVo;

import java.util.List;
import java.util.Map;

/**
 * @Description qubaolai
 * @Date 2020/2/17 12:36
 * @Description 文件上传
 */
public interface FileService {
    /**
     * 图片上传
     * @param param
     * @return
     */
    public ResultVo uploadFile(Map<String, Object> param);

    /**
     * 落库
     * @param path
     */
    public void addLbt(String path);

    /**
     * 获取图片
     * @return
     */
    public List<Map<String, String>> getAllPhoto();

    /**
     * 删除文件
     * @param ids
     */
    public void deleteFile(List<Integer> ids);

    /**
     * 修改图片信息
     * @param param
     */
    public void updateFilePath(Map<String, Object> param);

    /**
     * 删除服务器指定名称文件
     * @param fileName
     */
    public void deleteServerFile(String fileName);
}
