package com.tiantong.controller;

import com.tiantong.mapper.MusicMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    MusicMapper musicMapper;

    @GetMapping("test")
    @ApiOperation(value = "测试")
    public Integer firstPartyHome(){
         int a =musicMapper.test();
        return a;
    }
}
