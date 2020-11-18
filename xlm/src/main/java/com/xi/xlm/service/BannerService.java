package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.Banner;
import com.xi.xlm.request.ListBannerRequest;


public interface BannerService extends IService<Banner>{
    IPage<Banner> list(ListBannerRequest request);

    void addBanner(Banner banner);

    void delById(String id);

    void updateBanner(Banner banner);

    Banner getById(String id);

    void upstate(Banner banner);
}
