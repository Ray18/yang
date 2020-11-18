package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.IntegralGoods;
import com.xi.xlm.request.m.ShowIntegralGoodsListReq;
import com.xi.xlm.service.IIntegralGoodsService;
import com.xi.xlm.service.IIntegralGoodsSpecService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Controller
@RequestMapping("/m/integralGoods")
@AllArgsConstructor
public class IntegralGoodsController extends BaseController {

    private IIntegralGoodsService iIntegralGoodsService;
    private IIntegralGoodsSpecService iIntegralGoodsSpecService;



    @GetMapping(value = "show")
    @RequiresPermissions("hotel:integralGoods")

    public String show() {
        return "/hotel/integralGoods/list";
    }



    @GetMapping(value = "showList")
    @ResponseBody
    public Result showIntegralGoodsList(ShowIntegralGoodsListReq req, Long page, Long limit) {
        Page<IntegralGoods> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        QueryWrapper<IntegralGoods> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.hasText(req.getGoodsName()),IntegralGoods.GOODS_NAME,req.getGoodsName());
        wrapper.eq(req.getUpState()!=null, IntegralGoods.UP_STATE,req.getUpState());
        wrapper.orderByDesc(IntegralGoods.CREATE_DATE);
        IPage<IntegralGoods> list = iIntegralGoodsService.page(pages,wrapper);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdate")
    @RequiresPermissions("hotel:updateIntegralGoods")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iIntegralGoodsService.getById(id));
        return "/hotel/integralGoods/update";
    }

    @GetMapping(value = "toAdd")
    @RequiresPermissions("hotel:toAddIntegralGoods")
    public String toAddIntegralGoods() {
        return "/hotel/integralGoods/add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(IntegralGoods parameter) {
        iIntegralGoodsService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
    @RequiresPermissions("hotel:integralGoodsDel")
    @ResponseBody
    public Result del(String id) {
        iIntegralGoodsService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(IntegralGoods parameter) {
        iIntegralGoodsService.updateById(parameter);
        return Result.ok();
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        IntegralGoods parameter = iIntegralGoodsService.getById(id);
        parameter.setUpState(type);
        iIntegralGoodsService.updateById(parameter);
        return Result.ok();
    }
    

}
