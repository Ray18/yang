package com.xi.xlm.controller.w;

import cn.hutool.core.bean.BeanUtil;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCustomization;
import com.xi.xlm.request.w.SaveCustomizationReq;
import com.xi.xlm.response.w.GetCustomizationConfigResp;
import com.xi.xlm.service.IHotelCustomizationConfigService;
import com.xi.xlm.service.IHotelCustomizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @className: WHotelCustomizationController
 * @Description:个性定制前台控制器
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 14:03
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/w/hotel/customization")
@AllArgsConstructor
@Api(tags = "B-个性定制相关操作")
public class WHotelCustomizationController {


    private IHotelCustomizationConfigService iHotelCustomizationConfigService;
    private IHotelCustomizationService iHotelCustomizationService;


    @GetMapping("getCgetCustomizationConfigonfig")
    @ApiOperation("获取分类列表")
    public Result<GetCustomizationConfigResp> getCustomizationConfig() {
        GetCustomizationConfigResp resp = new GetCustomizationConfigResp();
        resp.setHeightList(iHotelCustomizationConfigService.getConfigByType(1));
        resp.setBedCore(iHotelCustomizationConfigService.getConfigByType(2));
        resp.setSpecialMaterial(iHotelCustomizationConfigService.getConfigByType(3));
        resp.setSize(iHotelCustomizationConfigService.getConfigByType(4));
        return Result.ok(resp);
    }


    @ApiOperation("保存个性定制")
    @PostMapping("saveConfig")
    public Result saveConfig(@RequestBody SaveCustomizationReq req) {
        HotelCustomization customization = BeanUtil.toBean(req, HotelCustomization.class);
        return Result.ok(iHotelCustomizationService.save(customization));
    }


}
