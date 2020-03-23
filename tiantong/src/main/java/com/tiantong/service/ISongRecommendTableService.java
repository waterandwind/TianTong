package com.tiantong.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.Music;
import com.tiantong.model.SongRecommendTable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface ISongRecommendTableService extends IService<SongRecommendTable> {
    List<Music> getRecommandSongList(IPage iPage);
}
