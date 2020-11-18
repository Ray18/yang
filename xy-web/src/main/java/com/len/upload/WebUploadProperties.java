package com.len.upload;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取配置文件中的配置
 */
@Component
public class WebUploadProperties {

    /**
     * 上传路径
     *  /表示上传到项目根目录,否则上传到指定目录
     */
    @Value("${lenosp.uploadPath}")
    private String uploadPath;

    /**
     * 根目录名称
     * 	默认uploadfiles
     */
    private String rootDirName;

    public String getUploadPath() {
        if(StringUtils.isBlank(uploadPath)){
            uploadPath = "/";
        }
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getRootDirName() {
        if(StringUtils.isBlank(rootDirName)) {
            if ("/".equals(getUploadPath())) {//上传到项目根目录
                rootDirName = "/uploadfiles";
            } else {
                rootDirName = uploadPath.substring(uploadPath.lastIndexOf("/"));
            }
        }
        return rootDirName;
    }
}
