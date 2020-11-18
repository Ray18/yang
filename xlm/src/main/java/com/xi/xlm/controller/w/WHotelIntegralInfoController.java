package com.xi.xlm.controller.w;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.HotelIntegralInfo;
import com.xi.xlm.enums.ParameterEnum;
import com.xi.xlm.service.IHotelIntegralInfoService;
import com.xi.xlm.service.IParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分信息表 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/w/hotel/hotelIntegralInfo")
@AllArgsConstructor
@Api(tags = "B-酒店-积分账户")
public class WHotelIntegralInfoController {
    private IParameterService iParameterService;
    private IHotelIntegralInfoService iHotelIntegralInfoService;

    @GetMapping("getGF")
    @ApiOperation("获取积分规则")
    public Result getGF() {

        return Result.ok(iParameterService.getParameterByCode(ParameterEnum.JFZH_JFGZ.value()));
    }

    @GetMapping("getIntegralList")
    public Result getIntegralList(@ApiParam(name = "type", required = false, value = "") String type) {
        QueryWrapper<HotelIntegralInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelIntegralInfo.MEMBER_ID, UPrincipal.getMember().getId());
        wrapper.eq(StringUtils.hasText(type), HotelIntegralInfo.INTEGRAL_TYPE, type);

        return Result.ok(iHotelIntegralInfoService.list(wrapper));
    }


}
