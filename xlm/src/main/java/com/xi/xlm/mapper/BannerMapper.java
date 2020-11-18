package com.xi.xlm.mapper;

import com.len.base.BaseMapper;
import com.xi.xlm.entity.Banner;
import com.xi.xlm.entity.Goods;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BannerMapper  extends BaseMapper<Banner> {

    @Insert("insert into attrac_banner(id,banner_img,banner_details,create_date,up_state) values(#{id},#{bannerImg},#{bannerDetails},#{createDate},#{upState})")
    void addBanner(Banner banner);

    @Delete("delete from attrac_banner where id=#{id}")
    void delById(String id);
//
    @Update("update attrac_banner set banner_img = #{bannerImg}, banner_details = #{bannerDetails}  where id = #{id}")
    void updateBanner(Banner banner);

    @Select("Select * from attrac_banner where id = #{id}")
    Banner getById(String id);

    @Update("update attrac_banner set up_state = #{upState} where id = #{id}")
    void upstate(Banner banner);
}