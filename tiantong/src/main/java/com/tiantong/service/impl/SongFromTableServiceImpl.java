package com.tiantong.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.SongFromTableMapper;
import com.tiantong.model.*;
import com.tiantong.service.ISongFromTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class SongFromTableServiceImpl extends ServiceImpl<SongFromTableMapper, FormInfo> implements ISongFromTableService {
@Autowired
SongFromTableMapper songFromTableMapper;
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

    @Override
    public List<FormInfoDto> getFormList(Integer accountId) {
        return   songFromTableMapper.getFormInfoDto(accountId);
    }

    @Override
    public FormMusicListDto getFormMusicList(Integer formId) {
        List<Music> list =  songFromTableMapper.getFormMusicList(formId);
        FormMusicListDto dto= new FormMusicListDto();
        dto.setMusicList(list);
        dto.setPosterUrl(songFromTableMapper.getFormPoster(formId));
        FormInfo formInfo=songFromTableMapper.selectById(formId);
        dto.setFormId(formId);
        dto.setFormName(formInfo.getSongFormName());
        return dto;
    }
}
