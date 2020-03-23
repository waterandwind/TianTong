package com.tiantong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tiantong.model.Music;
import com.tiantong.model.SongRecommendTable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface SongRecommendTableMapper extends BaseMapper<SongRecommendTable> {
        List<Music>  getRecommandSongList(IPage page);
}
