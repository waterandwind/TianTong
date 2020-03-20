package com.tiantong.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantong.model.*;
import com.tiantong.service.ISongerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/songer")
public class SongerController {
@Autowired
ISongerService iSongerService;
    @GetMapping("getSingerList")
    @ApiOperation(value = "获取歌手列表")
    public Response addAccount(QueryDto dto) {
        IPage<SingerInfo> page=new Page<>(dto.getCurrent(),dto.getPageSize());
        List<SingerInfo> rs=iSongerService.getSingerList(page,dto.getState());

        if (rs!=null){
            page.setRecords(rs);
            return Response.success("获取成功",page);
        }
        return Response.versionError("获取失败");
    }
}

