package com.len.util;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;


/**
 * @Author YangTianFeng
 * @Description  公共ajax回执类
 * @Date 9:40 2020/8/14
 * @Param 
 * @return 
 **/
@Data
public class LenResponse {

    //默认成功
    private boolean flag = true;
    private String msg;
    private JSONObject josnObj;
    private Integer status;
    private Object data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public LenResponse() {
    }

    public LenResponse(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public LenResponse(boolean flag, String msg, Integer status) {
        this.flag = flag;
        this.msg = msg;
        this.status = status;
    }

    /**
     * restful 返回
     */
    public static LenResponse error(String msg) {
        return new LenResponse(false, msg);
    }

    public static LenResponse sucess(String msg) {
        return new LenResponse(true, msg);
    }
}
