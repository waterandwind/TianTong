package com.tiantong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiantong.model.SlideImg;
import com.tiantong.model.SlideWithMusicDto;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface SlideImgMapper extends BaseMapper<SlideImg> {
    List<SlideWithMusicDto> getSlideAndMusic();
}
