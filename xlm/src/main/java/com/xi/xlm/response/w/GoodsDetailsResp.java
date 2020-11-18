package com.xi.xlm.response.w;

import com.xi.xlm.entity.IntegralGoodsSpec;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @className: GoodsDetailsResp
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/17 11:24
 * @Version: 1.0
 **/
@ApiModel("积分商品详情")
@Data
public class GoodsDetailsResp {

    private String img1;

    private String img2;

    private String img3;


    /**
     * 图文详情
     */
    @ApiModelProperty("图文详情")
    private String goodsDetails;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 销量
     */
    @ApiModelProperty("销量")
    private Integer salesCount;

    /**
     * 所需积分
     */
    @ApiModelProperty("所需积分")
    private Integer integral;


    /**
     * 是否新品 1是 0否
     */
    @ApiModelProperty("是否新品")
    private Boolean newProduct;

    /**
     * 封面
     */
    @ApiModelProperty("封面")
    private String coverImg;

    @ApiModelProperty("商品规格列表")
    List<IntegralGoodsSpec> specList;

}
