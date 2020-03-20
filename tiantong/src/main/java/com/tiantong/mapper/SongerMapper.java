package com.tiantong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tiantong.model.SingerInfo;
import com.tiantong.model.Songer;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface SongerMapper extends BaseMapper<Songer> {
    List<SingerInfo> getSingerList(IPage page, Integer state);
}
