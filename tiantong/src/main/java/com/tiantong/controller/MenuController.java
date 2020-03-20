package com.tiantong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantong.mapper.AccountMapper;
import com.tiantong.model.*;
import com.tiantong.service.IAccountMenuService;
import com.tiantong.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lls
 * @since 2020-03-19
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService iMenuService;
    @Autowired
    IAccountMenuService iAccountMenuService;
    @PostMapping("addMenu")
    @ApiOperation(value = "增加菜单")
    public Response addAccount(@RequestBody Menu menu) {
       boolean rs = iMenuService.save(menu);
       if (rs){
           return Response.success("菜单添加完成");
       }else {
           return Response.bizError("菜单添加失败");
       }
    }
    @PostMapping("editMenu")
    @ApiOperation(value = "编辑菜单")
    public Response editMenu(@RequestBody Menu menu) {
        boolean rs = iMenuService.updateById(menu);
        if (rs){
            return Response.success("菜单修改完成");
        }else {
            return Response.bizError("菜单修改失败");
        }
    }
    @PostMapping("batchEditMenu")
    @ApiOperation(value = "停启用菜单")
    public Response batchEditMenu(@RequestBody BatchDto dto) {
        List<Menu> menuList=new ArrayList<>();
        for (Integer id:
             dto.getIdList()) {
            Menu tem=new Menu();
            tem.setState(dto.getState());
            tem.setId(id);
            menuList.add(tem);
        }
        boolean rs = iMenuService.updateBatchById(menuList);
        if (rs){
            return Response.success("菜单修改完成");
        }else {
            return Response.bizError("菜单修改失败");
        }
    }
    @GetMapping("getMenuList")
    @ApiOperation(value = "获取菜单列表")
    public Response getMenuList(QueryDto menu) {
        QueryWrapper<Menu> qw=new QueryWrapper<>();
        qw.eq("state",menu.getState());
        IPage rs = iMenuService.page(new Page<Menu>(menu.getCurrent(),menu.getPageSize()),qw);
        if (rs!=null){
            return Response.success("获取成功",rs);
        }else {
            return Response.bizError("获取失败");
        }
    }
    @PostMapping("addMenuToAccountType")
    @ApiOperation(value = "给账号类型赋予菜单")
    public Response addMenuToAccountType(@RequestBody MenuDto dto) {
        Map<String,Object> map= new HashMap<>();
        map.put("account_type",dto.getType());
                    iAccountMenuService.removeByMap(map);
        List<AccountMenu> list=new ArrayList<>();
        for (Integer id:
             dto.getIdList()) {
            AccountMenu tem=new AccountMenu();
            tem.setAccountType(dto.getType());
            tem.setMenuId(id);
            list.add(tem);
        }
        boolean rs = iAccountMenuService.saveBatch(list);
        if (rs){
            return Response.success("用户菜单修改完毕");
        }else {
            return Response.bizError("用户菜单修改完成");
        }
    }

    @GetMapping("getUserMenu")
    @ApiOperation(value = "查询用户及其菜单列表")
    public Response getUserMenu() {

        List<UserMenuDto> rs = iMenuService.getUserMenu();
        if (rs!=null){
            return Response.success("获取成功",rs);
        }else {
            return Response.bizError("获取失败");
        }
    }
}

