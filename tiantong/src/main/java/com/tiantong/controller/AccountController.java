package com.tiantong.controller;


import com.tiantong.config.Utils;
import com.tiantong.model.*;
import com.tiantong.service.IAccountService;
import com.tiantong.service.ISongerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
       if (iAccountService.getById(updateModel)==null){
           return Response.versionError("旧密码错误");
       }else {
           updateModel.setPassword(Utils.getMD5(passwordDto.getNewPassword()));
           iAccountService.updateById(updateModel);
           return Response.success("新密码更新成功");
       }

    }
    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Response login(@RequestBody Account account) {

        AccountDto account1= new AccountDto();
        Account qacct=iAccountService.login(account);
        if (qacct!=null) {
         BeanUtils.copyProperties(qacct,account1);;
        String token = Utils.uuidStr();
        redis.opsForValue().set(token, account1.getAccount(), 30000, TimeUnit.SECONDS);
        account1.setToken(token);
        if (account1!=null&&account1.getType()==0){
            return Response.success("用户登录成功",account1);
        }else if(account1!=null&&account1.getType()==1){
            SingerInfo singerInfo=iSongerService.getSingerInfo(account1);
            return Response.success("歌手登录成功",singerInfo);
         }
        }
        return Response.versionError("账号或密码错误");
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
}

