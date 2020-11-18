package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.IntegralOrder;
import com.xi.xlm.request.m.IntegralOrderReq;
import com.xi.xlm.service.IIntegralOrderService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @since 2020-09-15
 */
@Controller
@RequestMapping("/m/integralOrder")
@AllArgsConstructor
public class IntegralOrderController extends BaseController {

    private IIntegralOrderService iIntegralOrderService;


    @GetMapping(value = "show")
    @RequiresPermissions("hotel:integralOrder")
    public String show() {
        return "/hotel/integralOrder/list";
    }


    @GetMapping(value = "showList")
    @ResponseBody
    public Result showList(IntegralOrderReq req, Long page, Long limit) {
        Page<IntegralOrder> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        QueryWrapper<IntegralOrder> wrapper = new QueryWrapper<>();

        wrapper.like(StringUtils.isNotEmpty(req.getPhone()), IntegralOrder.PHONE, req.getPhone());
        wrapper.like(StringUtils.isNotEmpty(req.getProductName()), IntegralOrder.PRODUCT_NAME, req.getProductName());
        wrapper.like(StringUtils.isNotEmpty(req.getTakePersonName()), IntegralOrder.TAKE_PERSON_NAME, req.getTakePersonName());
        wrapper.eq(req.getOrderState() != null, IntegralOrder.ORDER_STATE, req.getOrderState());
        wrapper.apply(StringUtils.isNotEmpty(req.getCreateDate()), "DATE_FORMAT( create_date, '%Y-%m-%d' ) " +
                " = DATE_FORMAT( '" + req.getCreateDate() + "', '%Y-%m-%d' )");

        IPage<IntegralOrder> list = iIntegralOrderService.page(pages, wrapper);
        return Result.ok(list);
    }


    @PostMapping("send")
    @ResponseBody
    public Result send(String id) {
        IntegralOrder order = iIntegralOrderService.getById(id);
        order.setOrderState(3);

        return Result.ok(iIntegralOrderService.updateById(order));
    }


}
