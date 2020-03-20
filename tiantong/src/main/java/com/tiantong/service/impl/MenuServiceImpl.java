package com.tiantong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tiantong.mapper.MenuMapper;
import com.tiantong.model.Menu;
import com.tiantong.model.UserMenuDto;
import com.tiantong.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
@Autowired
MenuMapper menuMapper;
    @Override
    public List<Menu> getMenuList(Menu menu) {
        QueryWrapper<Menu> qw=new QueryWrapper<>();
        qw.eq("state",menu.getState());
        return menuMapper.selectList(qw);
    }

    @Override
    public List<UserMenuDto> getUserMenu() {
        List<UserMenuDto>  list=new ArrayList<>();
        UserMenuDto user=new UserMenuDto();
        user.setType(0);
        user.setMenuList(menuMapper.getUserMenu(0));
        list.add(user);
        UserMenuDto singer=new UserMenuDto();
        singer.setType(1);
        singer.setMenuList(menuMapper.getUserMenu(1));
        list.add(singer);
        return list;
    }
}
