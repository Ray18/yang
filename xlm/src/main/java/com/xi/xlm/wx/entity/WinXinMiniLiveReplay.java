package com.xi.xlm.wx.entity;

import lombok.Data;

import java.util.List;

/**
 * @className: WinXinMiniLiveReplay
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/29 9:53
 * @Version: 1.0
 **/
@Data
public class WinXinMiniLiveReplay {

    /**
     * live_replay : [{"expire_time":"","create_time":"","media_url":""}]
     * errcode : 0
     * total : 1
     * errmsg : ok
     */

    private int errcode;
    private int total;
    private String errmsg;
    private List<LiveReplayBean> live_replay;


    @Data
    public static class LiveReplayBean {
        /**
         * expire_time :
         * create_time :
         * media_url :
         */

        private String expire_time;
        private String create_time;
        private String media_url;

    }
}
