package com.tiantong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tiantong.model.Account;
import com.tiantong.model.SingerInfo;
import com.tiantong.model.SingerSearchDto;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface AccountMapper extends BaseMapper<Account> {
    List<SingerInfo> searchSinger(String keyWord);
    List<SingerInfo> searchSingerByTypeAndSex(IPage page,SingerSearchDto dto);
}
