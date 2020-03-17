package com.tiantong.controller;


import com.tiantong.model.FormSongTable;
import com.tiantong.model.Response;
import com.tiantong.model.Songer;
import com.tiantong.service.ISongerService;
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
@RequestMapping("/songer")
public class SongerController {
@Autowired
ISongerService iSongerService;
    @PostMapping("addForm")
    @ApiOperation(value = "创建歌单")
    public Response addAccount(@RequestBody Songer songer) {
        boolean rs=iSongerService.updateById(songer);
        if (rs){
            return Response.success("歌手状态修改完成");
        }
        return Response.versionError("歌手状态修改完成");
    }
}

