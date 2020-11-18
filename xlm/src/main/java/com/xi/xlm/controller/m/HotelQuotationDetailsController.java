package com.xi.xlm.controller.m;


import com.len.base.BaseController;
import com.xi.xlm.service.IHotelQuotationDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 报价产品清单 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-21
 */
@Controller
@RequestMapping("/m/hotelQuotationDetails")
@AllArgsConstructor
public class HotelQuotationDetailsController extends BaseController {

        private IHotelQuotationDetailsService iHotelQuotationDetailsService;

}
