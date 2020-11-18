package com.xi.xlm.controller.m;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.AttractProduct;
import com.xi.xlm.request.ListGoodsRequest;
import com.xi.xlm.request.m.AddAttractProduct;
import com.xi.xlm.service.IAttractProductService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-24
 */
@Controller
@RequestMapping("/m/attractProduct")
public class AttractProductController {

    @Autowired
    private IAttractProductService iAttractProductService;


    @GetMapping(value = "showAttractProduct")
    @RequiresPermissions("attract:productShow")
    public String show() {
        return "/attrac/joinProduct/product/product-list";
    }


    @GetMapping(value = "showAttractProductList")
    @ResponseBody
    @RequiresPermissions("attract:productShow")
    public Result productShow(Long page, Long limit) {
        ListGoodsRequest request = new ListGoodsRequest();
        Page<AttractProduct> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<AttractProduct> list = iAttractProductService.list(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toAdd")
    @RequiresPermissions("attract:productAdd")
    public String add() {
        return "/attrac/joinProduct/product/product-add";
    }

    @PostMapping("add")
    @ResponseBody
    public Result add(AddAttractProduct product) {

        AttractProduct attractProduct = BeanUtil.toBean(product, AttractProduct.class);
        if (iAttractProductService.save(attractProduct)) {
            return Result.ok();
        } else {
            return Result.error("添加失败");
        }
    }

    @PostMapping("del")
    @RequiresPermissions("attract:productDel")
    @ResponseBody
    public Result del(String id) {
        iAttractProductService.removeById(id);
        return Result.ok();
    }

    @GetMapping("toUpdate")
    @RequiresPermissions("attract:productUpdate")
    public String update(String id, Model model) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        AttractProduct product = iAttractProductService.getById(id);
        model.addAttribute("pro", product);
        return "/attrac/joinProduct/product/product-update";
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(AddAttractProduct product) {
        AttractProduct attractProduct = BeanUtil.toBean(product, AttractProduct.class);
        if (iAttractProductService.updateById(attractProduct)) {
            return Result.ok();
        } else {
            return Result.error("修改失败");
        }
    }

    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, String type) {
        AttractProduct attractProduct = iAttractProductService.getById(id);

        if (type.equals("up")) {
            attractProduct.setUpState(true);

        }
        if (type.equals("down")) {
            attractProduct.setUpState(false);
        }
        iAttractProductService.updateById(attractProduct);
        return Result.ok();
    }

}
