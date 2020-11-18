package com.xi.xlm.mapper;

import com.len.base.BaseMapper;
import com.xi.xlm.entity.AttracJoinContent;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AttracJoinContentMapper extends BaseMapper<AttracJoinContent> {


    @Update("update attrac_join_content set details = #{details}  where id = #{id}")
    void updateJoinContent(AttracJoinContent attracCityCase);

    @Select("Select * from attrac_join_content where id = #{id}")
    AttracJoinContent getById(String id);
}