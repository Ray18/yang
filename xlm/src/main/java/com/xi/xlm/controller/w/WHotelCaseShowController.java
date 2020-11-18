package com.xi.xlm.controller.w;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCaseShow;
import com.xi.xlm.request.w.CaseShowListReq;
import com.xi.xlm.service.IHotelCaseShowService;
import com.xi.xlm.service.IHotelCaseShowTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @className: HotelCaseShowController
 * @Description:案例展示相关
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 13:01
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/w/hotel/caseShow")
@AllArgsConstructor
@Api(tags = "B-案例展示相关")
public class WHotelCaseShowController {
    private IHotelCaseShowService iHotelCaseShowService;
    private IHotelCaseShowTypeService iHotelCaseShowTypeService;


    @ApiOperation("案例展示分类列表")
    @PostMapping("caseShowTypeList")
    public Result caseShowTypeList() {

        return Result.ok(iHotelCaseShowTypeService.list());
    }


    @ApiOperation("案例展示列表")
    @PostMapping("caseShowList")
    public Result caseShowList(@RequestBody CaseShowListReq req) {
        QueryWrapper<HotelCaseShow> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelCaseShow.UP, true);
        wrapper.like(StringUtils.hasText(req.getName()), HotelCaseShow.CASE_NAME, req.getName());
        wrapper.eq(StringUtils.hasText(req.getTypeId()), HotelCaseShow.TYPE, req.getTypeId());

        return Result.ok(iHotelCaseShowService.list(wrapper));
    }


    @ApiOperation("根据ID查询案例展示")
    @GetMapping("getCaseShowById/{id}")
    public Result getCaseShowById(@PathVariable String id) {
        return Result.ok(iHotelCaseShowService.getById(id));
    }

}
