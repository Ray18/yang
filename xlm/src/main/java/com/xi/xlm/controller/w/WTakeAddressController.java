package com.xi.xlm.controller.w;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.TakeAddress;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.request.w.TakeAddressReq;
import com.xi.xlm.service.ITakeAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 收货地址 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-07
 */
@RestController
@Api(tags = "A-收货地址相关")
@Slf4j
@RequestMapping("/w/takeAddress")
public class WTakeAddressController {

    @Autowired
    private ITakeAddressService iTakeAddressService;


    @ApiOperation("查询收货地址列表-传入用户id")
    @GetMapping("list")
    public Result list() {
        WebMember webMember = UPrincipal.getMember();
        QueryWrapper<TakeAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(TakeAddress.MEMBER_ID, webMember.getId());
        return Result.ok(iTakeAddressService.list(wrapper));
    }


    @ApiOperation("添加个人地址")
    @PostMapping("saveAddress")
    public Result saveAddress(@RequestBody TakeAddressReq req) {
        if (req.getMemberId() == null) {
            WebMember webMember = UPrincipal.getMember();
            req.setMemberId(webMember.getId());
        }
        TakeAddress takeAddress = BeanUtil.toBean(req, TakeAddress.class);
        iTakeAddressService.save(takeAddress);
        return Result.ok();
    }


    @ApiOperation("根据id查询个人地址")
    @GetMapping("getAddressById/{id}")
    public Result getAddressById(@PathVariable("id") String id) {
        return Result.ok(iTakeAddressService.getById(id));
    }


    @ApiOperation("修改个人地址")
    @PostMapping("updateAddress")
    public Result updateAddress(@RequestBody TakeAddressReq req) {
        if (!StringUtils.hasText(req.getId())) {
            return Result.error("请传入id");
        }
        if (req.getMemberId() == null) {
            WebMember webMember = UPrincipal.getMember();
            req.setMemberId(webMember.getId());
        }

        TakeAddress takeAddress = BeanUtil.toBean(req, TakeAddress.class);
        if (takeAddress.getIsDefault()) {
            //处理默认地址
            isDefault(req.getId());
        }

        return Result.ok(iTakeAddressService.updateById(takeAddress));
    }


    @ApiOperation("修改是否默认")
    @GetMapping("isDef")
    public Result isDef(
            @ApiParam(name = "addressId", required = true, value = "地址id") String addressId,
            @ApiParam(name = "def", required = false, value = "是否默认/true /false")
                    boolean def) {
        if (def) {
            isDefault(addressId);
            return Result.ok();
        } else {
            TakeAddress takeAddress = iTakeAddressService.getById(addressId);
            takeAddress.setIsDefault(false);
            return Result.ok(iTakeAddressService.updateById(takeAddress));
        }


    }


    @ApiOperation("删除")
    @GetMapping("/del/{id}")
    public Result del(@PathVariable String id) {

        return Result.ok(iTakeAddressService.removeById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean isDefault(String id) {
        TakeAddress takeAddress = iTakeAddressService.getById(id);

        QueryWrapper<TakeAddress> wrapper = new QueryWrapper<>();
        wrapper.eq(TakeAddress.MEMBER_ID, takeAddress.getMemberId());
        wrapper.eq(TakeAddress.IS_DEFAULT, true);
        List<TakeAddress> list = iTakeAddressService.list(wrapper);
        list.forEach(l -> {
            l.setIsDefault(false);
        });
        iTakeAddressService.updateBatchById(list);


        takeAddress.setIsDefault(true);
        return iTakeAddressService.updateById(takeAddress);
    }


}
