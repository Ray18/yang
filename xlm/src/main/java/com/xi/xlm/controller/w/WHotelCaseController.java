package com.xi.xlm.controller.w;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.LngAndLatUtil;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCase;
import com.xi.xlm.request.w.CaseListReq;
import com.xi.xlm.response.w.ListCaseResp;
import com.xi.xlm.service.IHotelCaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 酒店版块-合作伙伴 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/w/hotel/hotelCase")
@Api(tags = "B-酒店-合作伙伴")
@AllArgsConstructor
public class WHotelCaseController {

    private IHotelCaseService iHotelCaseService;


    @GetMapping("getHotelById/{id}")
    @ApiOperation("根据id查询")
    public Result getHotelById(@PathVariable String id) {

        return Result.ok(iHotelCaseService.getById(id));
    }

    @PostMapping(value = "getCaseList")
    @ResponseBody
    @ApiOperation("获取合作伙伴列表")
    public Result getCaseList(@RequestBody CaseListReq req) {

        List<ListCaseResp> respList = new ArrayList<>();
        QueryWrapper<HotelCase> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelCase.UP, true);
        wrapper.eq(req.getHotelType() != null, HotelCase.HOTEL_TYPE, req.getHotelType());
        wrapper.like(StringUtils.hasText(req.getTitle()), HotelCase.PROJECT_NAME, req.getTitle());

        //地区等于空的时候直接报错
        if (!StringUtils.hasText(req.getArea())) {
            if (StringUtils.isEmpty(req.getLat()) || StringUtils.isEmpty(req.getLng())) {

                return Result.error("");
            }
            List<HotelCase> list = iHotelCaseService.list(wrapper);
            list.forEach(c -> {
                ListCaseResp r = BeanUtil.toBean(c, ListCaseResp.class);
                double JL = LngAndLatUtil.getDistance(Double.valueOf(req.getLng()), Double.valueOf(req.getLat()), Double.valueOf(c.getLng()), Double.valueOf(c.getLat()));
                r.setDistance(JL);
                respList.add(r);
            });
            Collections.sort(respList, (r1, r2) -> r2.getDistance().intValue() - r1.getDistance().intValue());

            return Result.ok(respList);

        } else {
            wrapper.eq(HotelCase.CITY, req.getArea());
            wrapper.orderByDesc(HotelCase.CASE_CLASS);
            List<HotelCase> list = iHotelCaseService.list(wrapper);
            list.forEach(c -> {
                ListCaseResp r = BeanUtil.toBean(c, ListCaseResp.class);
                respList.add(r);
            });

            return Result.ok(respList);

        }


    }


}
