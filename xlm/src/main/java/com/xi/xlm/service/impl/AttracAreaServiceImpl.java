package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttracArea;
import com.xi.xlm.mapper.AttracAreaMapper;
import com.xi.xlm.service.IAttracAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-07
 */
@Service
public class AttracAreaServiceImpl extends ServiceImpl<AttracAreaMapper, AttracArea> implements IAttracAreaService {
    @Autowired  AttracAreaMapper aam;
    @Override
    public IPage<AttracArea> list(Page<AttracArea> pages,String type) {
        LambdaQueryWrapper<AttracArea> attracAreaLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attracAreaLambdaQueryWrapper.eq(AttracArea::getInvalid,0);
        if(type.equals("0")){
            attracAreaLambdaQueryWrapper.eq(AttracArea::getParent,0);
        }else{
            attracAreaLambdaQueryWrapper.eq(AttracArea::getParent,type);
        }
        return baseMapper.selectPage(pages, attracAreaLambdaQueryWrapper);
    }

    @Override
    public List<AttracArea> areaByParentId(String parentId) {
        List<AttracArea> attracAreas = aam.selectList(new LambdaQueryWrapper<AttracArea>().eq(AttracArea::getParent, parentId));
        return attracAreas;
    }

    @Override
    public Integer addArea(AttracArea attracArea) {
        List<AttracArea> attracAreas = aam.selectList(new LambdaQueryWrapper<AttracArea>().eq(AttracArea::getName, attracArea.getName()));
        if(attracAreas.size()>0){
            return 0;
        }
        return aam.addAttracArea(attracArea);
    }

    @Override
    public Integer delById(String id) {
        return aam.delById(id);
    }

    @Override
    public Integer updateArea(AttracArea attracArea) {
        return aam.updateAttracArea(attracArea);
    }
}
