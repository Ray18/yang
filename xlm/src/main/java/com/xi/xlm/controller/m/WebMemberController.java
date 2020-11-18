package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-02
 */
@Controller
@RequestMapping("/m/webMember")
@AllArgsConstructor
public class WebMemberController extends BaseController {

    private IWebMemberService iWebMemberService;


    @GetMapping("show")
    @RequiresPermissions("member:show")
    public  String show(){

        return "/member/list";
    }


    @GetMapping("showList")
    @ResponseBody
    public Result showList(Long page,Long limit){
        Page<WebMember>webMemberPage = new Page<>();
        webMemberPage.setCurrent(page);
        webMemberPage.setSize(limit);

        return Result.ok(iWebMemberService.listPage(webMemberPage));
    }

}
