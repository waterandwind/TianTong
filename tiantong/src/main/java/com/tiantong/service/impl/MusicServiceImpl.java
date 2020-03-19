package com.tiantong.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.AccountMapper;
import com.tiantong.mapper.MusicMapper;
import com.tiantong.model.Music;
import com.tiantong.model.SingerInfo;
import com.tiantong.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements IMusicService {
    @Autowired
    MusicMapper musicMapper;
    @Autowired
    AccountMapper accountMapper;
    @Override
    public List<Music> getSingerMusic(Integer songerId,Integer flag) {
        Map<String ,Object> map=new HashMap<>();
        map.put("songer_id",songerId);
        map.put("state",flag);
        return musicMapper.selectByMap(map);
    }
    @Override
    public List<Music> searchMusicByName(String name) {
        QueryWrapper<Music> qw=new QueryWrapper();
        qw.like("name",name)
        .eq("state",1);
        return musicMapper.selectList(qw);
    }

    @Override
    public List<SingerInfo> searchSingerInfo(String name) {
        return accountMapper.searchSinger("%"+name+"%");
    }
}
