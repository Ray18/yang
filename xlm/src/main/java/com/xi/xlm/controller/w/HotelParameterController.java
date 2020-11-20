package com.xi.xlm.controller.w;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.entity.Fwzx;
import com.xi.xlm.entity.Parameter;
import com.xi.xlm.enums.ParameterEnum;
import com.xi.xlm.mapper.FwzxMapper;
import com.xi.xlm.response.w.FWZXInfoResp;
import com.xi.xlm.service.IParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: HotelCaseShowController
 * @Description:案例展示相关
 * @author:by yangtianfeng
 * @classDate: 2020/9/14 13:01
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/w/hotel/parameter")
@AllArgsConstructor
public class HotelParameterController {
    private IParameterService iParameterService;
   @Autowired
   private FwzxMapper wzxMapper;


    @GetMapping("getCaseShowById/{id}")
    public Result getCaseShowById(@PathVariable String code) {
        return Result.ok(iParameterService.getParameterByCode(code));
    }

    @GetMapping("getFWCN")
    public Result getFWCN() {
        return Result.ok(iParameterService.getParameterByCode(ParameterEnum.FWCN.value()));
    }


    @GetMapping("getFWZX")
    public Result getFWZX() {
        QueryWrapper<Parameter> wrapper = new QueryWrapper<>();

        FWZXInfoResp resp = new FWZXInfoResp();
        resp.setDetails(iParameterService.getParameterByCode(ParameterEnum.FWZX.value()).getDetails());
        resp.setWx(iParameterService.getParameterByCode(ParameterEnum.FWZX_WX.value()).getParValue());
        resp.setTel(iParameterService.getParameterByCode(ParameterEnum.FWZX_TEL.value()).getParValue());
        resp.setZj(iParameterService.getParameterByCode(ParameterEnum.FWZX_ZJ.value()).getParValue());
        return Result.ok(resp);
    }

    @GetMapping("getFWZX2")
    public Result getFWZX2() {
        LambdaQueryWrapper<Fwzx> fwzxLambdaQueryWrapper = new LambdaQueryWrapper<>();
        fwzxLambdaQueryWrapper.eq(Fwzx::getId,1);
        Fwzx fwzx = wzxMapper.selectOne(fwzxLambdaQueryWrapper);
        fwzx.setDetails(iParameterService.getParameterByCode(ParameterEnum.FWZX.value()).getDetails());
        return Result.ok(fwzx);
    }
}
