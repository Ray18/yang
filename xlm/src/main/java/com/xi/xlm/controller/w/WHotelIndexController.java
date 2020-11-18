package com.xi.xlm.controller.w;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.entity.*;
import com.xi.xlm.enums.ParameterEnum;
import com.xi.xlm.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @className: HotelIndexController
 * @Description:酒店版块首页数据
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 12:51
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/w/hotel/index")
@AllArgsConstructor
@Api(tags = "B-酒店版块首页内容")
public class WHotelIndexController {

    private IHotelBannerService iHotelBannerService;
    private AttracJoinContentService attracJoinContentService;
    private IHotelProductService iHotelProductService;
    private IParameterService iParameterService;
    private IHotelInformationService iHotelInformationService;
    private IHotelIntegralInfoService iHotelIntegralInfoService;
    private IWalkContentService iWalkContentService;

    @ApiOperation("banner列表")
    @PostMapping("bannerList")
    public Result bannerList() {
        QueryWrapper<HotelBanner> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelBanner.UP_STATE, true);

        return Result.ok(iHotelBannerService.list(wrapper));
    }

    @ApiOperation("根据ID查询banner")
    @GetMapping("getBannerById/{id}")
    public Result getBannerById(@PathVariable String id) {
        return Result.ok(iHotelBannerService.getById(id));
    }

    @ApiOperation("走进喜临门")
    @PostMapping("getJiaMengInfo")
    public Result getJiaMengInfo(@ApiParam(name = "type", required = true) String type) {
        QueryWrapper<AttracJoinContent> wrapper = new QueryWrapper<>();
        wrapper.eq(AttracJoinContent.TYPE, type);
        return Result.ok(attracJoinContentService.getOne(wrapper));
    }


    @ApiOperation("新-走进喜临门")
    @PostMapping("getWalkXlm")
    public Result getWalkXlm() {
        return Result.ok(iWalkContentService.list());
    }



    @PostMapping("getRecommendProductList")
    @ApiOperation("推荐产品")
    public Result getRecommendProductList() {

        List<HotelProduct> list = new ArrayList<>();
        QueryWrapper<HotelProduct> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelProduct.UP, true);
        List<HotelProduct> listInfo = iHotelProductService.list(wrapper);
        int s = 4;
        if (listInfo.size() < 4) {
            s = listInfo.size();
        }
        for (int i = 0; i < s; i++) {
            Random random = new Random();
            int n = random.nextInt(listInfo.size());
            HotelProduct p = listInfo.get(n);
            list.add(p);

        }

        return Result.ok(list);
    }


    @ApiOperation("商务洽谈")
    @GetMapping("getSWQT")
    public Result getFWCN() {
        return Result.ok(iParameterService.getParameterByCode(ParameterEnum.SWQT.value()));
    }


    @ApiOperation("获取官方资讯")
    @GetMapping("getInformation")
    public Result getInformation() {
        QueryWrapper<HotelInformation> informationQueryWrapper = new QueryWrapper<>();
        informationQueryWrapper.eq(HotelInformation.TYPE, 2);
        informationQueryWrapper.orderByDesc(HotelInformation.CREATE_BY).last("limit 0 ,4");
        return Result.ok(iHotelInformationService.list(informationQueryWrapper));
    }


    @ApiOperation("获取积分信息")
    @GetMapping("getIntegralInfo")
    public Result getIntegralInfo() {
        QueryWrapper<HotelIntegralInfo> informationQueryWrapper = new QueryWrapper<>();
        informationQueryWrapper.eq(HotelIntegralInfo.INTEGRAL_TYPE, 2);
        return Result.ok(iHotelIntegralInfoService.list(informationQueryWrapper));
    }

}
