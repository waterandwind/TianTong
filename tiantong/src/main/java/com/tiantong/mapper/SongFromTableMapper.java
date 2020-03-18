package com.tiantong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiantong.model.FormInfo;
import com.tiantong.model.FormInfoDto;
import com.tiantong.model.Music;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface SongFromTableMapper extends BaseMapper<FormInfo> {
    List<Music> getFormMusicList(Integer formId);
    List<FormInfoDto> getFormInfoDto(Integer accountId);
    String getFormPoster(Integer formId);
}
