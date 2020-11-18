package com.xi.xlm.controller.m;

import com.alibaba.fastjson.JSONObject;
import com.len.exception.MyException;
import com.len.util.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @className: MCommonController
 * @author:by yangtianfeng
 * @classDate: 2020/8/20 9:48
 * @Version: 1.0
 **/
@RestController
@RequestMapping("m/common")
@Api(value = "系统公用类接口", tags = "系统公用类接口")
@AllArgsConstructor
public class MCommonController {

    UploadUtil uploadUtil;

/*    @PostMapping("upload")
    @ApiOperation("文件上传")
    public Result upload(@RequestParam("file") MultipartFile file) {
        long size = file.getSize();
        if (size > 50000000) {
            throw new MyException("上传失败：文件大小不能超过50M");
        }
        try {

            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String path =  "/" + UUID.randomUUID().toString() + suffixName;
            String upload = uploadUtil.layUpload()upload(file.getInputStream());
            return Result.ok(upload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }*/


    /**
     * @return com.len.util.Result
     * @Author YangTianFeng
     * @Description layui图片上传
     * @Date 9:33 2020/8/21
     * @Param [file]
     **/
    @PostMapping("layUpload")
    @ApiOperation("layui文件上传")
    public String layUpload(@RequestParam("file") MultipartFile file) {
        long size = file.getSize();
        if (size > 50000000) {
            throw new MyException("上传失败：文件大小不能超过50M");
        }
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            String originalFilename = file.getOriginalFilename();
            //String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            //String path =  "/" + UUID.randomUUID().toString() + suffixName;

            String upload = uploadUtil.layUpload(file);

            jsonObject.put("code", 0);
            jsonObject.put("msg", "");
            data.put("src", upload);
            data.put("title", originalFilename);
            jsonObject.put("data", data);

            return jsonObject.toJSONString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
