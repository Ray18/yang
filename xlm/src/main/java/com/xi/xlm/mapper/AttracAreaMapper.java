package com.xi.xlm.mapper;

import com.xi.xlm.entity.AttracArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xi.xlm.entity.AttracArea;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}
