package com.tiantong.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.AccountMapper;
import com.tiantong.mapper.SongerMapper;
import com.tiantong.model.Account;
import com.tiantong.model.SingerInfo;
import com.tiantong.model.SingerSearchDto;
import com.tiantong.model.Songer;
import com.tiantong.service.ISongerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    SongerMapper songerMapper;
    @Autowired
    AccountMapper accountMapper;
    @Override
    public Boolean createDefaultInfo(Account account) {
        Songer songer=new Songer();
        songer.setAccountId(account.getId());
        songer.setState(-1);
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
        singerInfo.setRemark(songer.getRemark());
//        BeanUtils.copyProperties(songer,singerInfo);
        return singerInfo;
    }

    @Override
    public List<SingerInfo> getSingerList(IPage page,Integer state) {
        return songerMapper.getSingerList(page,state);
    }

    @Override
    public List<SingerInfo> serachSinger(IPage page, SingerSearchDto dto) {
        return accountMapper.searchSingerByTypeAndSex(page,dto);
    }
}
