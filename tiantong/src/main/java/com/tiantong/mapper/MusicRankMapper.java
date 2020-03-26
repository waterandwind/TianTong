package com.tiantong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiantong.model.Music;
import com.tiantong.model.MusicRank;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface MusicRankMapper extends BaseMapper<MusicRank> {
    List<Music> getRank(Integer type);
}
