package com.tiantong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.Account;
import com.tiantong.model.FormInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface ISongFromTableService extends IService<FormInfo> {
    Boolean createDefaultForm(Account account);
}
