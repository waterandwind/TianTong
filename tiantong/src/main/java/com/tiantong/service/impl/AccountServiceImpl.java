package com.tiantong.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.config.Utils;
import com.tiantong.mapper.AccountMapper;
import com.tiantong.model.Account;
import com.tiantong.service.IAccountService;
import com.tiantong.service.ISongFromTableService;
import com.tiantong.service.ISongerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    ISongFromTableService iSongFromTableService;
    @Autowired
    ISongerService songerService;
    @Override
    public Boolean createAccount(Account account) {
        account.setState(0);
        account.setPassword(Utils.getMD5(account.getPassword()));
        if (account.getType()==0){
            account.setAccountName("TianTong_"+new Date().getTime());
        }else if (account.getType()==1){
            account.setAccountName("未审核歌手");
        }
        boolean rs=save(account);
        QueryWrapper<Account> qw=new QueryWrapper<>();
        qw.eq("account",account.getAccount());
        account=accountMapper.selectOne(qw);
        if (account.getType()==0){
           return iSongFromTableService.createDefaultForm(account);
        }else if (account.getType()==1){ account.setAccountName("未审核歌手");
            return songerService.createDefaultInfo(account);
        }
        return false;
    }
    @Override
    public Boolean accountExist(Account account){
        Map<String,Object> map= new HashMap<>();
        map.put("account",account.getAccount());
        if(accountMapper.selectByMap(map).size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Account login(Account account) {
        QueryWrapper<Account> qw=new QueryWrapper<>();
        qw.eq("account",account.getAccount());
        qw.eq("password",Utils.getMD5(account.getPassword()));
        Account rs=accountMapper.selectOne(qw);
        return rs;
    }


}
