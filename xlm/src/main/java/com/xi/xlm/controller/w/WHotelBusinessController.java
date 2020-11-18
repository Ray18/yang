package com.xi.xlm.controller.w;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.HotelBusiness;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.enums.ParameterEnum;
import com.xi.xlm.request.w.SaveBusinessReq;
import com.xi.xlm.service.IHotelBusinessService;
import com.xi.xlm.service.IParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 酒店版块-商机提供 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@RestController
@RequestMapping("/w/hotel/hotelBusiness")
@Api(tags = "B-酒店-商机提供相关")
@AllArgsConstructor
public class WHotelBusinessController {

    private IHotelBusinessService iHotelBusinessService;
    private IParameterService iParameterService;


    @GetMapping("getGz")
    @ApiOperation("获取活动规则")
    public Result getGz() {

        return Result.ok(iParameterService.getParameterByCode(ParameterEnum.SJTG_HDGZ.value()));
    }


    @PostMapping("saveBusiness")
    public Result saveBusiness(@Valid @RequestBody SaveBusinessReq req) {
        WebMember webMember = UPrincipal.getMember();
        HotelBusiness business = BeanUtil.toBean(req, HotelBusiness.class);
        business.setMemberId(webMember.getId());
        business.setNickName(webMember.getNickName());
        business.setMemberPhone(webMember.getPhone());

        return Result.ok(iHotelBusinessService.save(business));
    }


    @GetMapping("getBusinessList")
    public Result getBusinessList(@ApiParam(name = "type", required = false, value = "") String type) {
        WebMember webMember = UPrincipal.getMember();
        QueryWrapper<HotelBusiness> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelBusiness.MEMBER_ID, webMember.getId());
        if (!StringUtils.isEmpty(type)) {
            if (type.equals("2")) {
                wrapper.in(HotelBusiness.AUDIT, 2, 3);
            } else {

                wrapper.eq(HotelBusiness.AUDIT, type);

            }

        }

        return Result.ok(iHotelBusinessService.list(wrapper));
    }


}
