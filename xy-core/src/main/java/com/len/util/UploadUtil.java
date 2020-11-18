package com.len.util;

import com.len.exception.MyException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by meng on 2018/5/8.
 * 文件上传工具类
 */
@Getter
@Setter
@ConfigurationProperties
@Component
public class UploadUtil {

    /**
     * 按照当日创建文件夹
     */
    @Value("${lenosp.isDayType}")
    private boolean isDayType;
    /**
     * 自定义文件路径
     */
    @Value("${lenosp.uploadPath}")
    private String uploadPath;

    @Value("${lenosp.localDomain}")
    private String localDomain;
    public static final String IMAGE_SUFFIX = "bmp,jpg,png,gif,jpeg";


    public UploadUtil() {
    }

    
    /**
     * @Author YangTianFeng
     * @Description  返回上传文件路劲
     * @Date 18:01 2020/8/20
     * @Param [inputStream, path]
     * @return java.lang.String
     **/
    public String layUpload(MultipartFile multipartFile) {
        if (isNull(multipartFile)) {
            throw new MyException("上传数据/地址获取异常");
        }

        LoadType loadType = fileNameStyle(multipartFile);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), loadType.getCurrentFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadType.getSrc();
    }


    /**
     * @Author YangTianFeng
     * @Description  返回上传文件名称
     * @Date 9:42 2020/8/20
     * @Param [multipartFile]
     * @return java.lang.String
     **/
    public String upload(MultipartFile multipartFile) {
        if (isNull(multipartFile)) {
            throw new MyException("上传数据/地址获取异常");
        }

        LoadType loadType = fileNameStyle(multipartFile);
        try {
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), loadType.getCurrentFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadType.getFileName();
    }




    /**
     * 格式化文件名 默认采用UUID
     *
     * @return
     */
    public LoadType fileNameStyle(MultipartFile multipartFile) {
        String curr = multipartFile.getOriginalFilename();
        String src ="";
        int suffixLen = curr.lastIndexOf(".");
        boolean flag=false;
        int index=-1;
        if("blob".equals(curr)){
            flag=true;
            index=0;
            curr=UUID.randomUUID() + ".png";
        } else if (suffixLen == -1) {
            throw new MyException("文件获取异常");
        }
        if(!flag){
            String suffix = curr.substring(suffixLen, curr.length());
            index = Arrays.binarySearch(IMAGE_SUFFIX.split(","),
                    suffix.replace(".", ""));

            curr = UUID.randomUUID() + suffix;
        }
        LoadType loadType = new LoadType();
        loadType.setFileName(curr);
        //image 情况
        src = localDomain + "file/" +curr;
        curr = uploadPath + File.separator + curr;

        loadType.setSrc(src);
        loadType.setCurrentFile(new File(curr));
        return loadType;
    }

    private boolean isNull(MultipartFile multipartFile) {
        if (null != multipartFile) {
            return false;
        }
        return true;
    }

}

@Data
class LoadType {
    private String fileName;
    private File currentFile;
    private String src;
}
