package com.tiantong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.SlideImg;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
public interface ISlideImgService extends IService<SlideImg> {
        List<SlideImg> getAllSlideImg();

}
