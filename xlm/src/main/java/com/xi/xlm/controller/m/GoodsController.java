package com.xi.xlm.controller.m;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.core.annotation.Log;
import com.len.util.Result;
import com.xi.xlm.entity.Goods;
import com.xi.xlm.request.ListGoodsRequest;
import com.xi.xlm.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

;

/**
 * @className: GoodsController
 * @Description:物资管理控制层
 * @author:by yangtianfeng
 * @classDate: 2020/5/7 15:44
 * @Version: 1.0
 **/
@RequestMapping("/qj/goods")
@Controller
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;


    @GetMapping(value = "showGoods")
    @RequiresPermissions("goods:show")
    public String showGoods(Model model) {
        return "/goods/goodsList";
    }

    @GetMapping(value = "showGoodsList")
    @ResponseBody
    @RequiresPermissions("goods:show")
    public Result showGoods(Long page, Long limit) {
        ListGoodsRequest request = new ListGoodsRequest();
        Page<Goods> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        request.setPage(pages);
        IPage<Goods> list = goodsService.list(request);
        return Result.ok(list);
    }

    @GetMapping(value = "showAddGood")
    public String showAddGood(Model model) {
        return "/goods/add-goods";
    }

    @Log(desc = "添加商品")
    @PostMapping(value = "addGood")
    @ResponseBody
    public Result addGood(Goods good) {
        good.setCreatedDate(LocalDateTime.now());
        good.setDel("0");
        goodsService.addGood(good);
        return Result.ok();
    }

    @Log(desc = "删除用户", type = Log.LOG_TYPE.DEL)
    @PostMapping(value = "delGood")
    @ResponseBody
    public Result delGood(String id) {
        goodsService.delById(id);
        return Result.ok();
    }

    @GetMapping(value = "updateGood")
    public String goUpdateUser(String id, Model model, boolean detail) {

        Goods goods = goodsService.getById(id);
        model.addAttribute("goods", goods);
        return "/goods/update-goods";
    }

    @PostMapping(value = "updateGoods")
    @ResponseBody
    public Result updateGoods(Goods goods) {
        goodsService.updateGood(goods);
        return Result.ok();
    }
}
