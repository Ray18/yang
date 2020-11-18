package com.xi.xlm.request.w;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: GetGoodsListReq
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/17 10:58
 * @Version: 1.0
 **/
@ApiModel("积分商城列表入参")
@Data
public class GetGoodsListReq {


        @ApiModelProperty("新品、true是/false否")
        private Boolean newGoods;
        @ApiModelProperty("人气  true、/false")
        private Boolean popularity;
        @ApiModelProperty("积分排序  true升序、/false降序")
        private Boolean integral;
        @ApiModelProperty("商品名")
        private String GoodsName;

}
