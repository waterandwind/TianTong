package com.tiantong.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.SongRecommendTableMapper;
import com.tiantong.model.Music;
import com.tiantong.model.SongRecommendTable;
import com.tiantong.service.ISongRecommendTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SongRecommendTableServiceImpl extends ServiceImpl<SongRecommendTableMapper, SongRecommendTable> implements ISongRecommendTableService {
@Autowired
SongRecommendTableMapper songRecommendTableMapper;
    @Override
    public List<Music> getRecommandSongList(IPage iPage) {
        return songRecommendTableMapper.getRecommandSongList(iPage);
    }
}
