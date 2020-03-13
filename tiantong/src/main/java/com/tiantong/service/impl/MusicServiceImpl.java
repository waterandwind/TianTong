package com.tiantong.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.MusicMapper;
import com.tiantong.model.Music;
import com.tiantong.service.IMusicService;
import org.springframework.stereotype.Service;

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

}
