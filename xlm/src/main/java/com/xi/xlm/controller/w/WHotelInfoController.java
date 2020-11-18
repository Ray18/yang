package com.xi.xlm.controller.w;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.HotelInfo;
import com.xi.xlm.request.w.SaveHotelInfoReq;
import com.xi.xlm.service.IHotelInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 酒店员工信息 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/w/hotel/hotelInfo")
@Api(tags = "B-酒店-员工通道")
@AllArgsConstructor
public class WHotelInfoController {
    private IHotelInfoService iHotelInfoService;


    @PostMapping("getInfoByMemberId")
    @ApiOperation("查询是否提交过")
    public Result getInfoByMemberId() {
        HotelInfo hotelInfo = iHotelInfoService.getOne(Wrappers.<HotelInfo>lambdaQuery().eq(HotelInfo::getMemberId, UPrincipal.getMember().getId()).ne(HotelInfo::getStatus, 2));
        return Result.ok(hotelInfo);
    }

    @PostMapping("saveInfo")
    @ApiOperation("提交")
    public Result saveInfo(@Valid @RequestBody SaveHotelInfoReq req) {
        HotelInfo hotelInfo = BeanUtil.toBean(req, HotelInfo.class);
        hotelInfo.setMemberId(UPrincipal.getMember().getId());
        return Result.ok(iHotelInfoService.save(hotelInfo));
    }


}
