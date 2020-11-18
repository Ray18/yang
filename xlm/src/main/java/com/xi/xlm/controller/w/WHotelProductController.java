package com.xi.xlm.controller.w;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.HotelProduct;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.request.w.GetProductListReq;
import com.xi.xlm.request.w.SaveHotelProduct;
import com.xi.xlm.service.IHotelProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 酒店版块-产品 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/w/hotel/hotelProduct")
@AllArgsConstructor
@Api(tags = "B-酒店-产品好无")
public class WHotelProductController {

    private IHotelProductService iHotelProductService;


    @GetMapping("getTypeList")
    @ApiOperation("获取所有分类")
    public Result getTypeList() {
        QueryWrapper<HotelProduct> wrapper = new QueryWrapper<>();
        wrapper.groupBy(HotelProduct.KCFL);
        return Result.ok(iHotelProductService.list(wrapper));
    }


    @PostMapping("getProductList")
    @ApiOperation("获取所有产品")
    public Result getProductList(@RequestBody GetProductListReq req) {
        QueryWrapper<HotelProduct> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.hasText(req.getProductName()), HotelProduct.CHAR016, req.getProductName());
        wrapper.eq(StringUtils.hasText(req.getTypeName()), HotelProduct.KCFL, req.getTypeName());
        wrapper.eq(HotelProduct.UP, true);
        List<HotelProduct> list = iHotelProductService.list(wrapper);

        WebMember webMember = UPrincipal.getMember();
        if (webMember == null) {
            list.forEach(a -> {
                a.setPrice(null);
            });
        } else {
            list.forEach(a -> {
                if (!webMember.getHotelFlag()) {
                    a.setPrice(null);
                }
            });

        }
        return Result.ok(list);
    }


    @GetMapping("getProductById/{id}")
    @ApiOperation("根据ID查询产品/{id}")
    public Result getProductById(@PathVariable String id) {
        HotelProduct a = iHotelProductService.getById(id);
        WebMember webMember = UPrincipal.getMember();
        if (webMember == null) {
            a.setPrice(null);
        } else {
            if (!webMember.getHotelFlag()) {
                a.setPrice(null);
            }

        }

        return Result.ok(a);
    }


    @PostMapping("product")
    public String product(@RequestBody SaveHotelProduct product) {
        JSONObject map = new JSONObject();

        HotelProduct hotelProductBean = BeanUtil.toBean(product, HotelProduct.class);

        if (product == null) {
            map.put("ZZT", "E");
            map.put("ZXX", "请传入产品");
            return map.toJSONString();
        }


        QueryWrapper<HotelProduct> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelProduct.CHAR016, product.getChar016());
        HotelProduct hotelProduct = iHotelProductService.getOne(wrapper);
        if (product.getDelFag().equals("0")) {
            if (hotelProduct == null) {
                map.put("ZZT", "S");
                map.put("ZXX", "del-errer-不存在此产品");
                return map.toJSONString();
            }
            map.put("ZZT", "S");
            map.put("ZXX", "del-success");
            iHotelProductService.removeById(hotelProduct.getId());
            return map.toJSONString();

        }


        if (hotelProduct != null) {
            hotelProductBean.setId(hotelProduct.getId());
            iHotelProductService.updateById(hotelProductBean);
        } else {

            iHotelProductService.save(hotelProductBean);
        }
        map.put("ZZT", "S");
        map.put("ZXX", "SUCCESS");
        return map.toJSONString();
    }

}
