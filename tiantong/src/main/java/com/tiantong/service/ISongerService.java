package com.tiantong.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.Account;
import com.tiantong.model.SingerInfo;
import com.tiantong.model.SingerSearchDto;
import com.tiantong.model.Songer;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface ISongerService extends IService<Songer> {
    Boolean createDefaultInfo(Account account);
    SingerInfo getSingerInfo(Account account);
    List<SingerInfo> getSingerList(IPage page,Integer state);
    List<SingerInfo> serachSinger(IPage page, SingerSearchDto dto);

}
