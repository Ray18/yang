package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelProduct;
import com.xi.xlm.service.IHotelProductService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Controller
@RequestMapping("/m/hotelProduct")
@AllArgsConstructor
public class HotelProductController extends BaseController {
    private IHotelProductService iHotelProductService;




    @GetMapping(value = "show")
    @RequiresPermissions("hotel:hotelProduct")
    public String show() {
        return "/hotel/hotelProduct/list";
    }



    @GetMapping(value = "showList")
    @ResponseBody
    public Result showHotelProductList( Long page, Long limit) {
        Page<HotelProduct> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelProduct> list = iHotelProductService.page(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdate")
   // @RequiresPermissions("hotel:updateHotelProduct")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iHotelProductService.getById(id));
        return "/hotel/hotelProduct/update";
    }






    @PostMapping("update")
    @ResponseBody
    public Result update(HotelProduct parameter) {
        iHotelProductService.updateById(parameter);
        return Result.ok();
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        HotelProduct parameter = iHotelProductService.getById(id);
        parameter.setUp(type);
        iHotelProductService.updateById(parameter);
        return Result.ok();
    }

}
