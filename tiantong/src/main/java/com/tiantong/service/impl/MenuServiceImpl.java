package com.tiantong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.MenuMapper;
import com.tiantong.model.Menu;
import com.tiantong.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lls
 * @since 2020-03-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
