package com.tiantong.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.SongFromTableMapper;
import com.tiantong.model.Account;
import com.tiantong.model.FormInfo;
import com.tiantong.service.ISongFromTableService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class SongFromTableServiceImpl extends ServiceImpl<SongFromTableMapper, FormInfo> implements ISongFromTableService {

    @Override
    public Boolean createDefaultForm(Account account) {
            List<FormInfo> list=new ArrayList<>();
            FormInfo myLove=new FormInfo();
            myLove.setAccountId(account.getId());
            myLove.setSongFormName("我的喜欢");
            myLove.setState(0);
            list.add(myLove);
            FormInfo myCollect=new FormInfo();
            myCollect.setAccountId(account.getId());
            myCollect.setSongFormName("我的收藏");
            myCollect.setState(0);
            list.add(myCollect);
          return   saveBatch(list);
    }
}
