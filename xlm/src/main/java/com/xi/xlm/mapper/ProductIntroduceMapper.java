package com.xi.xlm.mapper;

import com.len.base.BaseMapper;
import com.xi.xlm.entity.AttracCityCase;
import com.xi.xlm.entity.AttracProductIntroduce;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductIntroduceMapper extends BaseMapper<AttracProductIntroduce> {

    @Insert("insert into attrac_product_introduce(id,head_img,content_details,produc_name,type,type_name) values(#{id},#{headImg},#{contentDetails},#{producName},#{type},#{typeName})")
    void addIntroduce(AttracProductIntroduce attracProductIntroduce);

    @Delete("delete from attrac_product_introduce where id=#{id}")
    void delById(String id);

    @Update("update attrac_product_introduce set head_img = #{headImg}, content_details = #{contentDetails},produc_name = #{producName}  where id = #{id}")
    void updateIntroduce(AttracProductIntroduce attracProductIntroduce);

    @Select("Select * from attrac_product_introduce where id = #{id}")
    AttracProductIntroduce getById(String id);

}