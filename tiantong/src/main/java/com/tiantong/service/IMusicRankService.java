package com.tiantong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.Music;
import com.tiantong.model.MusicRank;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface IMusicRankService extends IService<MusicRank> {
        List<List<Music>> getRank();
}
