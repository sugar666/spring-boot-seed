package com.yangshu.seed.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author yangshu
 */

@Component
@Slf4j
public class FastDfsUtil {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    private final String prefixed = "http://39.106.161.191:8888";

    /**
     * MultipartFile类型的文件上传ַ
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /**
     * 普通的文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath path = fastFileStorageClient.uploadFile(inputStream, file.length(),
                FilenameUtils.getExtension(file.getName()), null);
        inputStream.close();
        return getResAccessUrl(path);
    }

    /**
     * 带输入流形式的文件上传
     *
     * @param is
     * @param size
     * @param fileName
     * @return
     */
    public String uploadFileStream(InputStream is, long size, String fileName) {
        StorePath path = fastFileStorageClient.uploadFile(is, size, fileName, null);
        return getResAccessUrl(path);
    }

    /**
     * 将一段文本文件写到fastdfs的服务器上
     *
     * @param content
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) throws IOException {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath path = fastFileStorageClient.uploadFile(stream, buff.length, fileExtension, null);
        stream.close();
        return getResAccessUrl(path);
    }

    public String uploadFileByURL(String coverPath) throws IOException {
        URL url = new URL(coverPath);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //通过输入流获取图片数据
        InputStream inputStream = conn.getInputStream();
        //注！IOUtils.toByteArray(url).length的大小如果错误则会出现图片上传成功但查看图片时图片部分丢失的情况
        StorePath path= fastFileStorageClient.uploadFile(inputStream, IOUtils.toByteArray(url).length,"jpg",null);
        inputStream.close();
        return getResAccessUrl(path);
    }

    /**
     * 返回文件上传成功后的地址名称ַ
     *
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        return prefixed + "/" + storePath.getGroup() + "/" + storePath.getPath();
    }
}
