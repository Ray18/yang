package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.WeixinLive;
import com.xi.xlm.entity.WeixinLiveReplay;
import com.xi.xlm.properties.WxAppProperties;
import com.xi.xlm.service.IWeixinLiveReplayService;
import com.xi.xlm.service.IWeixinLiveService;
import com.xi.xlm.wx.entity.WeiXinMiniLive;
import com.xi.xlm.wx.entity.WinXinMiniLiveReplay;
import com.xi.xlm.wx.util.WeiXinUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-08
 */
@Controller
@RequestMapping("/m/weixinLive")
public class WeixinLiveController extends BaseController {

    @Autowired
    private IWeixinLiveService iWeixinLiveService;
    @Autowired
    private WxAppProperties wxAppProperties;
    @Autowired
    private IWeixinLiveReplayService iWeixinLiveReplayService;

    @GetMapping(value = "show")
    @RequiresPermissions("attract:showLive")
    public String show() {

        return "attrac/live/list";
    }

    @GetMapping(value = "showList")
    @ResponseBody
    public Result showList(Long page, Long limit) {
        Page<WeixinLive> pg = new Page<>();
        pg.setCurrent(page);
        pg.setSize(limit);
        return Result.ok(iWeixinLiveService.page(pg));
    }

    @RequiresPermissions("attract:liveUpOrDown")
    @PostMapping(value = "upOrDown")
    @ResponseBody
    public Result upOrDown(String id, boolean type) {
        WeixinLive weixinLive = iWeixinLiveService.getById(id);
        weixinLive.setUp(type);
        return Result.ok(iWeixinLiveService.updateById(weixinLive));
    }


    @RequiresPermissions("attract:liveSyn")
    @GetMapping(value = "syn")
    @ResponseBody
    public Result syn() {
        WeiXinMiniLive weiXin = WeiXinUtil.getLiveInfo(WeiXinUtil.getAccessToken(wxAppProperties.getAppid(), wxAppProperties.getAppsecret()), 0, 100);
        if (weiXin.getRoom_info().size() > 0) {
            weiXin.getRoom_info().forEach(a -> {
                QueryWrapper<WeixinLive> weixinLiveQueryWrapper = new QueryWrapper<>();
                weixinLiveQueryWrapper.eq(WeixinLive.ROOMID, a.getRoomid());
                WeixinLive weixinLive = iWeixinLiveService.getOne(weixinLiveQueryWrapper);

                if (a.getLive_status() == 103) {
                    List<WeixinLiveReplay> liveReplays = new ArrayList<>();
                    WinXinMiniLiveReplay liveReplay = WeiXinUtil.getLiveInfoReplay(WeiXinUtil.getAccessToken(wxAppProperties.getAppid(), wxAppProperties.getAppsecret()), a.getRoomid());
                    liveReplay.getLive_replay().forEach(replay -> {
                        if(iWeixinLiveReplayService.getOne(Wrappers.<WeixinLiveReplay>lambdaQuery().eq(WeixinLiveReplay::getMediaUrl,replay.getMedia_url()))==null){
                            WeixinLiveReplay weixinLiveReplay = new WeixinLiveReplay();
                            weixinLiveReplay.setExpireTime(replay.getExpire_time());
                            weixinLiveReplay.setCreateTime(replay.getCreate_time());
                            weixinLiveReplay.setName(a.getName());
                            weixinLiveReplay.setMediaUrl(replay.getMedia_url());
                            liveReplays.add(weixinLiveReplay);

                        };

                    });
                    iWeixinLiveReplayService.saveBatch(liveReplays);
                }

                if (weixinLive == null) {
                    weixinLive = new WeixinLive();
                    weixinLive.setName(a.getName());
                    weixinLive.setRoomid(a.getRoomid());
                    weixinLive.setCoverImg(a.getCover_img());
                    weixinLive.setShareImg(a.getShare_img());
                    weixinLive.setLiveStatus(String.valueOf(a.getLive_status()));
                    LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(a.getStart_time(), 0, ZoneOffset.ofHours(8));
                    weixinLive.setStartTime(localDateTime);
                    LocalDateTime end = LocalDateTime.ofEpochSecond(a.getEnd_time(), 0, ZoneOffset.ofHours(8));
                    weixinLive.setEndTime(end);
                    weixinLive.setAnchorName(a.getAnchor_name());
                    weixinLive.setTotal(a.getTotal());
                    iWeixinLiveService.save(weixinLive);
                } else {
                    weixinLive.setName(a.getName());
                    weixinLive.setRoomid(a.getRoomid());
                    weixinLive.setCoverImg(a.getCover_img());
                    weixinLive.setShareImg(a.getShare_img());
                    weixinLive.setLiveStatus(String.valueOf(a.getLive_status()));
                    LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(a.getStart_time(), 0, ZoneOffset.ofHours(8));
                    weixinLive.setStartTime(localDateTime);
                    LocalDateTime end = LocalDateTime.ofEpochSecond(a.getEnd_time(), 0, ZoneOffset.ofHours(8));
                    weixinLive.setEndTime(end);
                    weixinLive.setAnchorName(a.getAnchor_name());
                    weixinLive.setTotal(a.getTotal());
                    iWeixinLiveService.updateById(weixinLive);
                }

            });


        }

        return Result.ok();
    }


}
