package com.tiantong.controller;


import com.tiantong.model.FormSongTable;
import com.tiantong.model.Response;
import com.tiantong.service.IFormSongTableService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
@RestController
@RequestMapping("/form_song_table")
public class FormSongTableController {
    @Autowired
    IFormSongTableService iFormSongTableService;
    @PostMapping("addForm")
    @ApiOperation(value = "创建歌单")
    public Response addAccount(@RequestBody FormSongTable music) {
        boolean rs=iFormSongTableService.save(music);
        if (rs){
            return Response.success("歌曲添加成功");
        }
        return Response.versionError("添加歌曲失败");
    }
}

