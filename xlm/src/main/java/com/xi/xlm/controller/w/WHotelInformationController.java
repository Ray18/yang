package com.xi.xlm.controller.w;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.entity.HotelInformation;
import com.xi.xlm.request.w.InformationListReq;
import com.xi.xlm.service.IHotelInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 酒店版块-官方资讯 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/w/hotel/hotelInformation")
@Api(tags = "B-酒店-官方资讯")
@AllArgsConstructor
public class WHotelInformationController {

    private IHotelInformationService iHotelInformationService;


    @ApiOperation("获取官方资讯")
    @PostMapping("getInformationList")
    public Result getInformationList(@RequestBody InformationListReq req) {
        QueryWrapper<HotelInformation> informationQueryWrapper = new QueryWrapper<>();
        informationQueryWrapper.eq(req.getIntegralType() != null, HotelInformation.TYPE, req.getIntegralType());
        informationQueryWrapper.like(StringUtils.hasText(req.getTitle()), HotelInformation.TITLE, req.getTitle());
        informationQueryWrapper.orderByDesc(HotelInformation.CREATE_BY);

        return Result.ok(iHotelInformationService.list(informationQueryWrapper));
    }


    @ApiOperation("根据ID获取官方资讯")
    @GetMapping("getInformationById/{id}")
    public Result getInformationList(@PathVariable String id) {

        return Result.ok(iHotelInformationService.getById(id));
    }

}
