package com.tiantong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.Account;
import com.tiantong.model.FormInfo;
import com.tiantong.model.Music;

import java.util.List;

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
    List<FormInfo> getFormList(Integer accountId);
    List<Music> getFormMusicList(Integer formId);
}
