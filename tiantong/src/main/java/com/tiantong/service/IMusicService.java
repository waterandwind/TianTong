package com.tiantong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.Music;
import com.tiantong.model.SingerInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface IMusicService extends IService<Music> {
    List<Music> getSingerMusic(Integer songerId,Integer flag);
    List<Music> searchMusicByName(String name);
//    List<> searchMusicByName(String name);
    List<SingerInfo> searchSingerInfo(String name);
}
