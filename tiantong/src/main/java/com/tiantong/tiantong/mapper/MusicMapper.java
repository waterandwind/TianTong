package com.tiantong.tiantong.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MusicMapper {
    @Select("select count(*) from music")
    int test();
}
