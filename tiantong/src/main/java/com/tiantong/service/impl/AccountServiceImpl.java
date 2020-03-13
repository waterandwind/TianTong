package com.tiantong.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.AccountMapper;
import com.tiantong.model.Account;
import com.tiantong.service.IAccountService;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
