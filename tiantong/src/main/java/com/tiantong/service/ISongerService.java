package com.tiantong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.Account;
import com.tiantong.model.SingerInfo;
import com.tiantong.model.Songer;

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

}
