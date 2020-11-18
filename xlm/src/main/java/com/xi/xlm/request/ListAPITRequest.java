package com.xi.xlm.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttracProductIntroduceType;
import com.xi.xlm.entity.Goods;
import lombok.Data;

/**
 * @className: ListGoodsRequest
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/8/14 10:49
 * @Version: 1.0
 **/
@Data
public class ListAPITRequest {
    private Page<AttracProductIntroduceType> page;
}

