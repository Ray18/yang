package com.xi.xlm.controller.w;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.AttracArea;
import com.xi.xlm.entity.AttracAreaVo;
import com.xi.xlm.mapper.AttracAreaMapper;
import com.xi.xlm.service.IAttracAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-07
 */
@Controller
@RequestMapping("/w/attracArea")
public class WAttracAreaController {
    @Autowired IAttracAreaService attracAreaService;
    @Autowired
    AttracAreaMapper aam;
    @GetMapping(value = "showAttracAreaList")
    @ResponseBody
//    @RequiresPermissions("attract:showAttracAreaShow")
    public Result areaShow() {
        List<AttracArea> attracAreas4=new ArrayList<AttracArea>();
        LambdaQueryWrapper<AttracArea> attracAreaLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attracAreaLambdaQueryWrapper.select(AttracArea::getId,AttracArea::getName,AttracArea::getParent);
        attracAreaLambdaQueryWrapper.eq(AttracArea::getInvalid,0);
        attracAreaLambdaQueryWrapper.eq(AttracArea::getParent,0);
        //全国的地区一级
        List<AttracArea> attracAreas = aam.selectList(attracAreaLambdaQueryWrapper);
        for (AttracArea attracArea : attracAreas) {
            LambdaQueryWrapper<AttracArea> attracAreaLambdaQueryWrapper2 = new LambdaQueryWrapper<>();
            attracAreaLambdaQueryWrapper2.select(AttracArea::getId,AttracArea::getName,AttracArea::getParent);
            attracAreaLambdaQueryWrapper2.eq(AttracArea::getInvalid,0);
            attracAreaLambdaQueryWrapper2.eq(AttracArea::getParent,attracArea.getId());
            //全国的地区二级
            List<AttracArea> attracAreas2 = aam.selectList(attracAreaLambdaQueryWrapper2);
            for (AttracArea area : attracAreas2) {
                LambdaQueryWrapper<AttracArea> attracAreaLambdaQueryWrapper3 = new LambdaQueryWrapper<>();
                attracAreaLambdaQueryWrapper3.select(AttracArea::getId,AttracArea::getName,AttracArea::getParent);
                attracAreaLambdaQueryWrapper3.eq(AttracArea::getInvalid,0);
                attracAreaLambdaQueryWrapper3.eq(AttracArea::getParent,area.getId());
                //全国的地区三级
                List<AttracArea> attracAreas3 = aam.selectList(attracAreaLambdaQueryWrapper3);
                attracAreas4.addAll(attracAreas3);
            }
        }
        return Result.ok(attracAreas4);
    }
}
