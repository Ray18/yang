package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.AttractProductOrder;
import com.xi.xlm.request.m.ListAttractProductOrder;
import com.xi.xlm.service.IAttractProductOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/m/attractProductOrder")
public class AttractProductOrderController extends BaseController {

    @Autowired
    private IAttractProductOrderService iAttractProductOrderService;

    @GetMapping("list")
    @RequiresPermissions("attract:productOrderShow")
    public String show() {
        return "/attrac/joinProduct/productOrder/list";
    }

    @GetMapping(value = "showList")
    @ResponseBody
    public Result showList(ListAttractProductOrder order, Long page, Long limit) {
        Page<AttractProductOrder> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<AttractProductOrder> list = iAttractProductOrderService.list(order, pages);
        return Result.ok(list);
    }


    @PostMapping(value = "send")
    @ResponseBody
    public Result send(String id) {
        Result result = new Result();
        AttractProductOrder order = iAttractProductOrderService.getById(id);
        if (order != null) {
            order.setOrderState(3);
            iAttractProductOrderService.updateById(order);
            result.setFlag(true);
            result.setMsg("发货成功");
        } else {
            result.setFlag(false);
            result.setMsg("请选择发货订单");

        }
        return result;
    }


}
