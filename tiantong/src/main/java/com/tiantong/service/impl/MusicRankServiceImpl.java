package com.tiantong.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.MusicRankMapper;
import com.tiantong.mapper.SongRecommendTableMapper;
import com.tiantong.model.Music;
import com.tiantong.model.MusicRank;
import com.tiantong.service.IMusicRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
@Service
public class MusicRankServiceImpl extends ServiceImpl<MusicRankMapper, MusicRank> implements IMusicRankService {
@Autowired
MusicRankMapper musicRankMapper;
@Autowired
SongRecommendTableMapper songRecommendTableMapper;
    @Override
    public List<List<Music>> getRank() {
        List<List<Music>> rsList=new ArrayList<>();
        for (int i=1;i<=5;i++){
            List<Music> tem=musicRankMapper.getRank(i);
            rsList.add(tem);
        }

        return rsList;
    }
}
