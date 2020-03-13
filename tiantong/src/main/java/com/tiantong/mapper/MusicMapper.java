package com.tiantong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiantong.model.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MusicMapper extends BaseMapper<Music> {
    Integer test();
}
