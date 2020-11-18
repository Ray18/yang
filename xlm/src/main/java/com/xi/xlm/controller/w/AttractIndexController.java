package com.xi.xlm.controller.w;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.*;
import com.xi.xlm.service.*;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: AttractIndexController
 * @Description: 招商首页信息
 * @author:by yangtianfeng
 * @classDate: 2020/9/4 13:51
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/w/att/index/")
@AllArgsConstructor
public class AttractIndexController extends BaseController {

    private BannerService bannerService;
    private ProductIntroduceService productIntroduceService;
    private ProductIntroduceTypeService productIntroduceTypeService;
    private AttracJoinContentService attracJoinContentService;
    private AttracCityCaseService attracCityCaseService;
    private IAttractCommissionInfoService iAttractCommissionInfoService;
    private IWeixinLiveService iWeixinLiveService;
    private IWeixinLiveReplayService iWeixinLiveReplayService;

    @PostMapping("getAttBanner")
    public Result getListBanner() {
        return Result.ok(bannerService.list());
    }


    @PostMapping("getProductType")
    public Result getProductType() {

        return Result.ok(productIntroduceTypeService.list());

    }

    @GetMapping("getProductIntroduce")
    public Result getProductIntroduce(@ApiParam(name = "typeId", required = false, value = "类别id") String typeId) {
        List<AttracProductIntroduce> list = new ArrayList<>();
        if (StringUtils.hasText(typeId)) {
            QueryWrapper<AttracProductIntroduce> wrapper = new QueryWrapper<>();
            wrapper.eq(AttracProductIntroduce.TYPE, typeId);
            list = productIntroduceService.list(wrapper);

        } else {
            list = productIntroduceService.list();
        }

        return Result.ok(list);

    }


    @GetMapping("getProductIntroduceById/{id}")
    public Result getProductIntroduceById(@PathVariable String id) {

        return Result.ok(productIntroduceService.getById(id));

    }

    @PostMapping("getJiaMengInfo")
    public Result getJiaMengInfo(@ApiParam(name = "type", required = true, value = "1加盟优势2加盟流程3加盟政策 ") String type) {
        QueryWrapper<AttracJoinContent> wrapper = new QueryWrapper<>();
        wrapper.eq(AttracJoinContent.TYPE, type);
        return Result.ok(attracJoinContentService.getOne(wrapper));
    }

    @GetMapping("getCityCase")
    public Result getCityCase(@ApiParam(name = "title", required = false, value = "title ") String title) {
        QueryWrapper<AttracCityCase> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.hasText(title), AttracCityCase.TITLE, title);
        return Result.ok(attracCityCaseService.list(wrapper));
    }

    @PostMapping("getYongJin")
    public Result getListYongJin() {

        return Result.ok(iAttractCommissionInfoService.listCommissionResp());
    }


    @PostMapping("getLiveList")
    public Result getLiveList() {
        QueryWrapper<WeixinLive> wrapper = new QueryWrapper<>();
        wrapper.eq(WeixinLive.UP, true);

        return Result.ok(iWeixinLiveService.list(wrapper));
    }


    @PostMapping("getLiveReplayList")
    public Result getLiveReplayList() {
        QueryWrapper<WeixinLiveReplay> wrapper = new QueryWrapper<>();
        return Result.ok(iWeixinLiveReplayService.list());
    }


}
