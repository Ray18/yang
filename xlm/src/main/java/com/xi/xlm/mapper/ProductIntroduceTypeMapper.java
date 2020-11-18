package com.xi.xlm.mapper;

import com.len.base.BaseMapper;
import com.xi.xlm.entity.AttracProductIntroduce;
import com.xi.xlm.entity.AttracProductIntroduceType;
import com.xi.xlm.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Mapper
public interface ProductIntroduceTypeMapper extends BaseMapper<AttracProductIntroduceType> {

    @Insert({"insert into attrac_product_introduce_type(id, name,create_date) values(#{id}, #{name},#{createDate})"})
    void AddType(AttracProductIntroduceType attracProductIntroduceType);

    @Delete("delete from attrac_product_introduce_type where id=#{id}")
    void delById(String id);

    @Update("update attrac_product_introduce_type set name = #{name} where id = #{id}")
    void updateType(AttracProductIntroduceType attracProductIntroduceType);

    @Select("Select * from attrac_product_introduce_type where id = #{id}")
    AttracProductIntroduceType getById(String id);

    @Select("Select * from attrac_product_introduce_type")
    ArrayList<AttracProductIntroduceType> selectAll();

    @Select("Select * from attrac_product_introduce where type = #{type}")
    AttracProductIntroduce getByType(String type);
}