package com.tiantong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.SlideImgMapper;
import com.tiantong.model.SlideImg;
import com.tiantong.service.ISlideImgService;
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
public class SlideImgServiceImpl extends ServiceImpl<SlideImgMapper, SlideImg> implements ISlideImgService {
@Autowired
SlideImgMapper slideImgMapper;
    @Override
    public List<SlideImg> getAllSlideImg() {
        return slideImgMapper.selectList(null);
    }
}
