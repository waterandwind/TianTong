package com.tiantong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantong.model.Menu;
import com.tiantong.model.Music;
import com.tiantong.model.QueryDto;
import com.tiantong.model.Response;
import com.tiantong.service.IMenuService;
import com.tiantong.service.IMusicRankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/music-rank")
public class MusicRankController {
    @Autowired
    IMusicRankService iMusicRankService;
    @GetMapping
    @ApiOperation("获取排行榜")
    public Response getAllRank() {

      List<List<Music>> rs = iMusicRankService.getRank();
        if (rs!=null){
            return Response.success("获取怕排行榜成功",rs);
        }else {
            return Response.bizError("获取失败");
        }
    }

}

