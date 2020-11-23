package com.xi.xlm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttracArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xi.xlm.entity.AttracArea;
import com.xi.xlm.entity.AttracAreaVo;
import org.apache.ibatis.annotations.*;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-07
 */
public interface AttracAreaMapper extends BaseMapper<AttracArea> {
    @Insert("insert into attrac_area(id,name,parent) values(#{id},#{name},#{parent})")
    Integer addAttracArea(AttracArea attracArea);

    @Delete("update attrac_area set invalid=1 where id=#{id}")
    Integer delById(String id);

    @Update("update attrac_area set name = #{name}  where id = #{id}")
    Integer updateAttracArea(AttracArea attracArea);

    @Select("Select * from attrac_area where id = #{id}")
    AttracArea getById(String id);

    IPage<AttracAreaVo> findVoPage(@Param("page")Page<AttracArea> page, @Param("ew")AttracArea attracAreaLambdaQueryWrapper);
}
