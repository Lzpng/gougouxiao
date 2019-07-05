package com.gougouxiao.shop.controller;

import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传控制器
 */

@RestController
public class UploadController {

    @Value("${fileServerUrl}")
    private String fileServerUrl;

    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam(value = "file") MultipartFile multipartFile) {

        Map<String,Object> data = new HashMap<>();
        //设置状态
        data.put("status", 500);
        try {
            //获取文件名
            String filename = multipartFile.getOriginalFilename();
            //获取文件字节数
            byte[] bytes = multipartFile.getBytes();
            // 上传文件到FastDFS文件服务器
            //获取fastdfs-client.conf配置文件
            String path = this.getClass().getResource("/fastdfs_client.conf").getPath();
            //初始化客户端全局对象
            ClientGlobal.init(path);
            //创建存储对象
            StorageClient storageClient = new StorageClient();
            //将文件上传到服务器,第二个参数是后缀名,获取上传文件路径
            String[] arr = storageClient.upload_file(bytes, FilenameUtils.getExtension(filename), null);
            //对ip地址与返回的url进行拼接
            StringBuilder sb = new StringBuilder(fileServerUrl);
            for (String str : arr) {
                sb.append("/").append(str);
            }
            data.put("status", 200);
            data.put("url", sb.toString());
            System.out.println(sb + ":" + sb.toString());
            return data;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return data;
    }

}
