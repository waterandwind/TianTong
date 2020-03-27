package com.tiantong.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public Response addForm(@RequestBody FormInfo formInfo) {
        if ("我的喜欢".equals(formInfo.getSongFormName())
        ||"我的收藏".equals(formInfo.getSongFormName())){
            return Response.bizError("非法的歌单名!");
        }
        formInfo.setState(1);
        boolean rs=iSongFromTableService.save(formInfo);
        if (rs){
            return Response.success("歌单添加成功");
        }
        return Response.versionError("添加歌单失败");
    }
    @PostMapping("editForm")
    @ApiOperation(value = "添加歌单")
    public Response editForm(@RequestBody FormInfo formInfo) {
        if ("我的喜欢".equals(formInfo.getSongFormName())
                ||"我的收藏".equals(formInfo.getSongFormName())){
            return Response.bizError("非法的歌单名!");
        }
        boolean rs=iSongFromTableService.updateById(formInfo);
        if (rs){
            return Response.success("歌单修改成功");
        }
        return Response.versionError("歌单修改失败");
    }
    @DeleteMapping("removeForm")
    @ApiOperation(value = "删除歌单")
    public Response removeForm(@RequestBody List<Integer> idList) {
        boolean rs=iSongFromTableService.removeByIds(idList);
        if (rs){
            return Response.success("歌单删除成功");
        }
        return Response.versionError("删除歌单失败");
    }
    @GetMapping("getFormList")
    @ApiOperation(value = "获取歌单列表")
    public Response getFormList( Integer accountId) {

        List<FormInfoDto> rs=iSongFromTableService.getFormList(accountId);
        if (rs!=null||rs.size()>0){
            return Response.success("获取歌单列表成功",rs);
        }
        return Response.versionError("获取歌单列表失败");
    }

    @GetMapping("getFormMusicList")
    @ApiOperation(value = "获取歌单歌曲列表")
    public Response getFormMusicList( Integer formId) {

        FormMusicListDto rs=iSongFromTableService.getFormMusicList(formId);
        if (rs.getMusicList()!=null){
            return Response.success("获取歌曲列表成功",rs);
        }
        return Response.versionError("获取歌曲列表失败");
    }
    @PostMapping("addMusicToForm")
    @ApiOperation(value = "添加歌曲至歌单")
    public Response addMusicToForm(@RequestBody FormSongTable formInfo) {
        QueryWrapper<FormSongTable> qw=new QueryWrapper<>();
        qw.eq("form_id",formInfo.getFormId());
        qw.eq("song_id",formInfo.getSongId());

        if (iFormSongTableService.getOne(qw)!=null){
            return Response.bizError("歌曲已存在于歌单中，请勿重复添加！");
        }
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

