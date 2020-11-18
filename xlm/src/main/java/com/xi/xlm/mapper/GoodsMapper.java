package com.xi.xlm.mapper;

import com.len.base.BaseMapper;
import com.xi.xlm.entity.Goods;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    @Insert({"insert into goods(id, goods_name, goods_num,goods_unit,goods_type,goods_use_day,goods_accumulate_num,created_date,del) values(#{id}, #{goodsName}, #{goodsNum},#{goodsUnit},#{goodsType},#{goodsUseDay},#{goodsAccumulateNum},#{createdDate},#{del})"})
    void AddGood(Goods good);


    @Delete("delete from goods where id=#{id}")
    void delById(String id);

    @Update("update goods set goods_num = #{goodsNum}, goods_unit = #{goodsUnit} ,goods_use_day = #{goodsUseDay} ,goods_accumulate_num =#{goodsAccumulateNum} where id = #{id}")
    void updateGood(Goods good);

    @Select("Select * from goods where id = #{id}")
    Goods getById(String id);
}