package com.imooc.mall.dao;

import com.imooc.mall.pojo.Catogory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
//@Mapper
public interface CatogoryMapper {

    @Select("select * from mall_category where id = #{id}")
    Catogory FindByID(@Param("id")Integer id);

    Catogory QueryByID(Integer id);
}
