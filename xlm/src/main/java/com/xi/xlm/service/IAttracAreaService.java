package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.AttracArea;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-07
 */
public interface IAttracAreaService extends IService<AttracArea> {

    IPage<AttracArea> list(Page<AttracArea> pages,String type);

    List<AttracArea> areaByParentId(String parentId);

    Integer addArea(AttracArea attracArea);

    Integer delById(String id);

    Integer updateArea(AttracArea attracArea);
}
