package com.xi.xlm.controller.w;


import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.WebMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前台用户 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-02
 */
@RestController
@Api(tags = "A-个人中心相关")
@Slf4j
@AllArgsConstructor
@RequestMapping("/w/webMember")
public class WMemberController {


    @ApiOperation("获取当前登陆用户的用户信息")
    @PostMapping("getUserInfo")
    public Result getUserInfo() {
        WebMember webMember = UPrincipal.getMember();
        return Result.ok(webMember);
    }



}
