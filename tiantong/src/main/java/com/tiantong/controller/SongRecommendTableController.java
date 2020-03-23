package com.tiantong.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantong.model.*;
import com.tiantong.service.ISongRecommendTableService;
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
@RequestMapping("/song-recommend-table")
public class SongRecommendTableController {
@Autowired
    ISongRecommendTableService iSongRecommendTableService;
    @GetMapping("getSingerList")
    @ApiOperation(value = "获取推荐歌曲列表")
    public Response addAccount() {

        List<Music> rs=iSongRecommendTableService.getRecommandSongList(null);

        if (rs!=null){

            return Response.success("获取成功",rs);
        }
        return Response.versionError("获取失败");
    }
    @PostMapping("addRecommendMusic")
    @ApiOperation(value = "添加推荐歌曲")
    public Response addRecommendMusic(@RequestBody SongRecommendTable songRecommendTable) {
       boolean rs=iSongRecommendTableService.save(songRecommendTable);
        if (rs){
            return Response.success("添加成功");
        }
        return Response.versionError("获取失败");
    }
//
    @DeleteMapping("removeRecommendMusic")
    @ApiOperation(value = "批量修改推荐")
    public Response batchRemoveMusic(@RequestBody SongRecommendTable songRecommendTable) {
        boolean rs=iSongRecommendTableService.removeById(songRecommendTable.getId());
        if (rs){
            return Response.success("移除推荐列表成功");
        }
        return Response.versionError("歌曲删除失败");
    }
}

