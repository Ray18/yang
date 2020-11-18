package com.xi.xlm.controller.m;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.ExcelUtil;
import com.len.util.Result;
import com.xi.xlm.entity.AttracArea;
import com.xi.xlm.entity.AttractEmployeeInfo;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.request.m.EmployeeInfoRequest;
import com.xi.xlm.service.IAttracAreaService;
import com.xi.xlm.service.IAttractEmployeeInfoService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
@Controller
@RequestMapping("/m/attractEmployeeInfo")
@AllArgsConstructor
@Slf4j
public class AttractEmployeeInfoController {

    private IAttractEmployeeInfoService iAttractEmployeeInfoService;
    private IAttracAreaService iAttracAreaService;
    private IWebMemberService iWebMemberService;

    @GetMapping(value = "showEmployeeInfo")
    @RequiresPermissions("attract:employeeInfoShow")
    public String show() {
        return "attrac/business/employeeInfo/list";
    }


    @GetMapping("shoList")
    @ResponseBody
    public Result showList(Long page, Long limit) {
        Page<AttractEmployeeInfo> infoIPage = new Page<>();
        infoIPage.setCurrent(page);
        infoIPage.setSize(limit);
        IPage<AttractEmployeeInfo> employeeInfoIPage = iAttractEmployeeInfoService.list(infoIPage);
        return Result.ok(employeeInfoIPage);
    }


    @PostMapping("del")
    @ResponseBody
    public Result del(String id) {
        AttractEmployeeInfo info = iAttractEmployeeInfoService.getById(id);
        WebMember webMember = iWebMemberService.getById(info.getMemberId());
        if (webMember != null) {
            webMember.setAttractFlag(false);
            iWebMemberService.updateById(webMember);
        }
        iAttractEmployeeInfoService.removeById(id);
        return Result.ok("删除成功");
    }

    /**
     * @return com.len.util.Result
     * @Author YangTianFeng
     * @Description 导入Excel
     * @Date 15:23 2020/8/31
     * @Param []
     **/
    @PostMapping("importExcel")
    @ResponseBody
    public Result importExcel(MultipartFile file) throws Exception {
        Object list = ExcelUtil.readExcel(file, new EmployeeInfoRequest(), 1);
        if (list == null) {
            return Result.error("请传入数据");
        }
        List<EmployeeInfoRequest> infoRequests = (List<EmployeeInfoRequest>) list;
        if (infoRequests.size() <= 0) {

            return Result.error("请传入数据");
        }

        List<AttractEmployeeInfo> infos = new ArrayList<>();
        infoRequests.forEach(a -> {
                    AttractEmployeeInfo ai = new AttractEmployeeInfo();
                    ai = BeanUtil.toBean(a, AttractEmployeeInfo.class);

                    //TODO:查找基础会员标识
                    //通过手机号查询基础会员信息
                    QueryWrapper<WebMember> webMemberQueryWrapper = new QueryWrapper<>();
                    webMemberQueryWrapper.like(WebMember.PHONE, ai.getPhone());
                    WebMember webMember = iWebMemberService.getOne(webMemberQueryWrapper);
                    if (webMember != null) {
                        //查询到会员，标识用户为招商员工
                        webMember.setAttractFlag(true);
                        ai.setMemberId(webMember.getId());
                        iWebMemberService.updateById(webMember);
                    }

                    //TODO 查找地区code
                    QueryWrapper<AttracArea> areaWrapper = new QueryWrapper<>();
                    areaWrapper.like(AttracArea.NAME, a.getAreaName());
                    AttracArea attracArea = iAttracAreaService.getOne(areaWrapper);
                    if (attracArea != null) {
                        ai.setAreaCode(attracArea.getId());
                    }


                    ai.setStatus(1);
                    infos.add(ai);
                }

        );
        iAttractEmployeeInfoService.saveBatch(infos);
        //全部保存完之后开始为员工整理上下级关系
        //遍历新增人员
        infos.forEach(info -> {
            //通过地区去寻找上级
            QueryWrapper<AttracArea> wrapper = new QueryWrapper<>();
            wrapper.eq(AttracArea.NAME, info.getAreaName());
            AttracArea attracArea = iAttracAreaService.getOne(wrapper);
            if (!attracArea.getParent().equals("0")) {
                //操作区域经理
                attracArea = iAttracAreaService.getById(attracArea.getParent());
                //通过区域CODE查询人员
                QueryWrapper<AttractEmployeeInfo> infoQueryWrapper = new QueryWrapper<>();
                infoQueryWrapper.eq(AttractEmployeeInfo.AREA_CODE, attracArea.getId());
                AttractEmployeeInfo employeeInfo = iAttractEmployeeInfoService.getOne(infoQueryWrapper);
                if (employeeInfo != null) {
                    //装入上级ID
                    info.setParentJob(employeeInfo.getId());
                    iAttractEmployeeInfoService.updateById(info);
                }
            }

        });

        return Result.ok();
    }


}
