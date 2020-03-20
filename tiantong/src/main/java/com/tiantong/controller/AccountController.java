package com.tiantong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantong.config.Utils;
import com.tiantong.mapper.MenuMapper;
import com.tiantong.model.*;
import com.tiantong.service.IAccountService;
import com.tiantong.service.IMenuService;
import com.tiantong.service.ISongerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    ISongerService iSongerService;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    private StringRedisTemplate redis;
    @PostMapping("addAccount")
    @ApiOperation(value = "账号注册")
    public Response addAccount(@RequestBody Account account) {
        boolean rs=false;
        if (iAccountService.accountExist(account)){
            return Response.bizError("账号已被注册！");
        }else if (account.getAccount()==null||account.getPassword()==null||account.getType()==null){
           return Response.bizError("存在未输入注册信息！");
        }else {
            if (iAccountService.createAccount(account)){
                return Response.success("注册成功");
            }
            return Response.bizError("注册失败");
        }
    }
    @PostMapping("updatePassword")
    @ApiOperation(value = "修改密码")
    public Response updatePassword(@RequestBody PasswordDto passwordDto) {
       Account updateModel=new Account();
       updateModel.setId(passwordDto.getId());
       updateModel.setPassword(Utils.getMD5(passwordDto.getOldPassword()));
       Account qaccount= iAccountService.getById(updateModel);
       if (qaccount!=null&&qaccount.getPassword().equals(updateModel.getPassword())){
           updateModel.setPassword(Utils.getMD5(passwordDto.getNewPassword()));
           iAccountService.updateById(updateModel);
           return Response.success("新密码更新成功");

       }else {
           return Response.versionError("旧密码错误");
       }

    }
    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Response login(@RequestBody Account account) {
        AccountDto account1= new AccountDto();
        Account qacct=iAccountService.login(account);

        if (qacct!=null) {
            if (qacct.getState()==1){
                return Response.versionError("账号已被停用");
            }
         BeanUtils.copyProperties(qacct,account1);;
        String token = Utils.uuidStr();
        redis.opsForValue().set(token, account1.getAccount(), 30000, TimeUnit.SECONDS);
        account1.setToken(token);
        if (account1!=null){
            account1.setMenuList(menuMapper.getUserMenu(account1.getType()));
        }
        if (account1!=null&&account1.getType()==0){
            return Response.success("用户登录成功",account1);
        }else if(account1!=null&&account1.getType()==1){
            SingerInfo singerInfo=iSongerService.getSingerInfo(account1);
            return Response.success("歌手登录成功",singerInfo);
         }else if(account1!=null&&account1.getType()==2){
            return Response.success("管理员登录成功",account1);
        }
        }
        return Response.versionError("账号或密码错误");
    }
    @PostMapping("getUserInfo")
    @ApiOperation(value = "获取用户信息")
    public Response getUserInfo(@RequestBody Account account) {

        AccountDto account1= new AccountDto();
        Account qacct=iAccountService.getById(account);
        if (qacct!=null) {
            BeanUtils.copyProperties(qacct,account1);;
            if (account1!=null&&account1.getType()==0){
                return Response.success("用户信息查询成功",account1);
            }else if(account1!=null&&account1.getType()==1){
                SingerInfo singerInfo=iSongerService.getSingerInfo(account1);
                return Response.success("歌手信息查询成功",singerInfo);
            }
        }
        return Response.versionError("信息查询失败");
    }
    @PostMapping("editAccount")
    @ApiOperation(value = "账号编辑")
    public Response updateAccount(@RequestBody SingerInfo account) {
        Account editModel=new Account();
        BeanUtils.copyProperties(account,editModel);

        Boolean rs=iAccountService.updateById(editModel);
        if (account.getType()==0&&rs){
            return Response.success("用户信息更新成功");
        }else if (account.getType()==1){
            Songer edSonger=new Songer();
            BeanUtils.copyProperties(account,edSonger);
            edSonger.setId(account.getSingerId());
            rs=iSongerService.updateById(edSonger);
            if (rs){
                return Response.success("歌手信息更新成功");
            }else {
                return Response.bizError("歌手信息更新失败");
            }
        }
        return Response.bizError("未进行更新");
    }

    @PostMapping("batchEditAccount")
    @ApiOperation(value = "停启用账号")
    public Response batchEditMenu(@RequestBody BatchDto dto) {
        List<Account> menuList=new ArrayList<>();
        for (Integer id:
                dto.getIdList()) {
            Account tem=new Account();
            tem.setState(dto.getState());
            tem.setId(id);
            menuList.add(tem);
        }
        boolean rs = iAccountService.updateBatchById(menuList);
        if (rs){
            return Response.success("用户修改完成");
        }else {
            return Response.bizError("用户修改失败");
        }
    }
    @GetMapping("getAccountList")
    @ApiOperation(value = "获取用户列表")
    public Response getMenuList(QueryDto account) {
        QueryWrapper<Account> qw=new QueryWrapper<>();
        qw.eq("state",account.getState());
        qw.ne("type",2);
        if (account.getKeyWord()!=null){
            qw.like("account",account.getKeyWord());
        }

        IPage rs = iAccountService.page(new Page<Account>(account.getCurrent(),account.getPageSize()),qw);
        if (rs!=null){
            return Response.success("获取成功",rs);
        }else {
            return Response.bizError("获取失败");
        }
    }

}

