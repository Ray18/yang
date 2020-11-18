package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.AttracJoinContent;
import com.xi.xlm.request.ListAJCRequest;

public interface AttracJoinContentService extends IService<AttracJoinContent> {
    IPage<AttracJoinContent> list(ListAJCRequest request);
    void updateJoinContent(AttracJoinContent attracJoinContent);
    AttracJoinContent getById(String id);
}
