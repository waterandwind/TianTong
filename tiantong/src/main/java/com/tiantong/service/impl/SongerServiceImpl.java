package com.tiantong.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.SongerMapper;
import com.tiantong.model.Account;
import com.tiantong.model.SingerInfo;
import com.tiantong.model.Songer;
import com.tiantong.service.ISongerService;
import org.springframework.beans.BeanUtils;
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
public class SongerServiceImpl extends ServiceImpl<SongerMapper, Songer> implements ISongerService {

    @Override
    public Boolean createDefaultInfo(Account account) {
        Songer songer=new Songer();
        songer.setAccountId(account.getId());
        songer.setState(0);
        return save(songer);
    }

    @Override
    public SingerInfo getSingerInfo(Account account) {
        SingerInfo singerInfo=new SingerInfo();
        BeanUtils.copyProperties(account,singerInfo);
        QueryWrapper<Songer> qw=new QueryWrapper<>();
        qw.eq("account_id",account.getId());
        Songer songer=getOne(qw);
        singerInfo.setInfo(songer.getInfo());
        singerInfo.setCheckState(songer.getState());
        singerInfo.setSingerType(songer.getSingerType());
        singerInfo.setSingerId(songer.getId());
//        BeanUtils.copyProperties(songer,singerInfo);
        return singerInfo;
    }
}
