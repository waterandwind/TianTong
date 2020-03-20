package com.tiantong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tiantong.model.Menu;
import com.tiantong.model.UserMenuDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lls
 * @since 2020-03-19
 */
public interface IMenuService extends IService<Menu> {
        List<Menu> getMenuList(Menu menu);
        List<UserMenuDto> getUserMenu();
}
