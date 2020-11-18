package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.IntegralGoodsSpec;
import com.xi.xlm.service.IIntegralGoodsService;
import com.xi.xlm.service.IIntegralGoodsSpecService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Wrapper;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Controller
@RequestMapping("/m/integralGoodsSpec")
@AllArgsConstructor
public class IntegralGoodsSpecController extends BaseController {

    private IIntegralGoodsService iIntegralGoodsService;
    private IIntegralGoodsSpecService iIntegralGoodsSpecService;


    @GetMapping(value = "show")
    public String show(Model model, String id) {
        model.addAttribute("id", id);

        return "/hotel/integralGoodsSpec/list";
    }


    @GetMapping(value = "showList")
    @ResponseBody
    public Result showIntegralGoodsList(Long page, Long limit,String id) {
        Page<IntegralGoodsSpec> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        QueryWrapper<IntegralGoodsSpec> wrapper = new QueryWrapper<>();
        wrapper.eq(IntegralGoodsSpec.GOODS_ID,id);
        IPage<IntegralGoodsSpec> list = iIntegralGoodsSpecService.page(pages,wrapper);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdate")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iIntegralGoodsSpecService.getById(id));
        return "/hotel/integralGoodsSpec/update";
    }

    @GetMapping(value = "toAdd")
    public String toAddIntegralGoods(Model model, String goodsId) {
        model.addAttribute("goodsId", goodsId);
        return "/hotel/integralGoodsSpec/add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(IntegralGoodsSpec parameter) {
        iIntegralGoodsSpecService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
    @ResponseBody
    public Result del(String id) {
        iIntegralGoodsSpecService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(IntegralGoodsSpec parameter) {
        iIntegralGoodsSpecService.updateById(parameter);
        return Result.ok();
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        IntegralGoodsSpec parameter = iIntegralGoodsSpecService.getById(id);
        iIntegralGoodsSpecService.updateById(parameter);
        return Result.ok();
    }


}
