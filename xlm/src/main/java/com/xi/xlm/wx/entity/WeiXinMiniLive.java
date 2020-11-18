package com.xi.xlm.wx.entity;

import lombok.Data;

import java.util.List;

/**
 * @className: WeiXinLive
 * @Description:微信直播间属性
 * @author:by yangtianfeng
 * @classDate: 2020/9/8 14:23
 * @Version: 1.0
 **/
@Data
public class WeiXinMiniLive {

    /**
     * errcode : 0
     * errmsg : ok
     * room_info : [{"name":"直播房间名","roomid":1,"cover_img":"peg","share_img":"peg","live_status":101,"start_time":1568128900,"end_time":1568131200,"anchor_name":"里斯","goods":[{"cover_img":"jpeg","url":"pages/index/index.html","price":1100,"name":"茶杯"}],"total":1}]
     */

    private int errcode;
    private String errmsg;
    private List<RoomInfoBean> room_info;

    @Data
    public static class RoomInfoBean {
        /**
         * name : 直播房间名
         * roomid : 1
         * cover_img : peg
         * share_img : peg
         * live_status : 101
         * start_time : 1568128900
         * end_time : 1568131200
         * anchor_name : 里斯
         * goods : [{"cover_img":"jpeg","url":"pages/index/index.html","price":1100,"name":"茶杯"}]
         * total : 1
         */
        private String name;
        private String roomid;
        private String cover_img;
        private String share_img;
        private int live_status;
        private int start_time;
        private int end_time;
        private String anchor_name;
        private int total;
        private List<GoodsBean> goods;
        @Data
        public static class GoodsBean {
            /**
             * cover_img : jpeg
             * url : pages/index/index.html
             * price : 1100
             * name : 茶杯
             */
            private String cover_img;
            private String url;
            private int price;
            private String name;


        }
    }
}
