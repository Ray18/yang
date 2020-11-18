package com.xi.xlm.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.entity.Banner;
import com.xi.xlm.mapper.BannerMapper;
import com.xi.xlm.request.ListBannerRequest;
import com.xi.xlm.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper,Banner> implements BannerService  {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public IPage<Banner> list(ListBannerRequest request) {
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        return bannerMapper.selectPage(request.getPage(), wrapper);
    }

    @Override
    public void addBanner(Banner banner) {
        bannerMapper.addBanner(banner);
    }

    @Override
    public void delById(String id) {
        bannerMapper.delById(id);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerMapper.updateBanner(banner);
    }

    @Override
    public Banner getById(String id) {
        return bannerMapper.getById(id);
    }

    @Override
    public void upstate(Banner banner) {
        bannerMapper.upstate(banner);
    }
}
