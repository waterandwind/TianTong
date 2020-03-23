package com.tiantong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiantong.model.Music;
import com.tiantong.model.SingerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MusicMapper extends BaseMapper<Music> {
    boolean addPlayNum(Integer id);
    boolean addLikeNum(Integer id);
    boolean addCollectNum(Integer id);
}
