package com.tiantong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiantong.model.Menu;
import com.tiantong.model.UserMenuDto;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lls
 * @since 2020-03-19
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getUserMenu(Integer type);
}
