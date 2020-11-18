package com.xi.xlm.mapper;

import com.len.base.BaseMapper;
import com.xi.xlm.entity.AttracCityCase;
import com.xi.xlm.entity.Banner;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AttracCityCaseMapper extends BaseMapper<AttracCityCase> {

    @Insert("insert into attrac_city_case(id,head_img,case_details,title) values(#{id},#{headImg},#{caseDetails},#{title})")
    void addCityCase(AttracCityCase attracCityCase);

    @Delete("delete from attrac_city_case where id=#{id}")
    void delById(String id);

    @Update("update attrac_city_case set head_img = #{headImg}, case_details = #{caseDetails},title = #{title}  where id = #{id}")
    void updateCityCase(AttracCityCase attracCityCase);

    @Select("Select * from attrac_city_case where id = #{id}")
    AttracCityCase getById(String id);
}