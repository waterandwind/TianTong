package com.tiantong.controller;


import com.tiantong.model.*;
import com.tiantong.service.IFormSongTableService;
import com.tiantong.service.IMusicService;
import com.tiantong.service.ISongFromTableService;
import com.tiantong.service.ISongerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/song_form_info_table")
public class SongFromTableController {
    @Autowired
    ISongFromTableService iSongFromTableService;
    @Autowired
    IFormSongTableService iFormSongTableService;
    @PostMapping("addForm")
    @ApiOperation(value = "添加歌单")
    public Response addAccount(@RequestBody FormInfo formInfo) {
        formInfo.setState(1);
        boolean rs=iSongFromTableService.save(formInfo);
        if (rs){
            return Response.success("歌单添加成功");
        }
        return Response.versionError("添加歌单失败");
    }

    @GetMapping("getFormList")
    @ApiOperation(value = "获取歌单列表")
    public Response getFormList( Integer accountId) {

        List<FormInfo> rs=iSongFromTableService.getFormList(accountId);
        if (rs!=null||rs.size()>0){
            return Response.success("获取歌单列表成功",rs);
        }
        return Response.versionError("获取歌单列表失败");
    }
    @GetMapping("getFormMusicList")
    @ApiOperation(value = "获取歌单歌曲列表")
    public Response getFormMusicList( Integer formId) {

        List<Music> rs=iSongFromTableService.getFormMusicList(formId);
        if (rs!=null||rs.size()>0){
            return Response.success("获取歌曲列表成功",rs);
        }
        return Response.versionError("获取歌曲列表失败");
    }
    @PostMapping("addMusicToForm")
    @ApiOperation(value = "添加歌曲至歌单")
    public Response addMusicToForm(@RequestBody FormSongTable formInfo) {
        boolean rs=iFormSongTableService.save(formInfo);
        if (rs){
            return Response.success("歌曲添加成功");
        }
        return Response.versionError("歌曲添加失败");
    }
    @PostMapping("removeMusicFromForm")
    @ApiOperation(value = "从歌单移除歌曲")
    public Response removeMusicFromForm(@RequestBody FormSongTable formInfo) {
        Map<String ,Object> map = new HashMap<>();
        map.put("form_id",formInfo.getFormId());
        map.put("song_id",formInfo.getSongId());
        boolean rs=iFormSongTableService.removeByMap(map);
        if (rs){
            return Response.success("歌曲移除成功");
        }
        return Response.versionError("歌曲移除失败");
    }

}

