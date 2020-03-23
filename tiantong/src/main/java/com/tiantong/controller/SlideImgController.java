package com.tiantong.controller;


import com.tiantong.model.Music;
import com.tiantong.model.Response;
import com.tiantong.model.SlideImg;
import com.tiantong.service.ISlideImgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/slide-img")
public class SlideImgController {
    @Autowired
    ISlideImgService iSlideImgService;

    @PostMapping("addSlideImg")
    @ApiOperation(value = "添加轮播图")
    public Response addAccount(@RequestBody SlideImg slideImg) {

        boolean rs= iSlideImgService.save(slideImg);
        if (rs){
            return Response.success("轮播图添加成功");
        }
        return Response.versionError("轮播图添加失败");
    }

    @DeleteMapping("deleteSlideImg")
    @ApiOperation(value = "删除轮播图")
    public Response deleteSlideImg(@RequestBody SlideImg slideImg) {

        boolean rs= iSlideImgService.removeById(slideImg.getId());
        if (rs){
            return Response.success("删除轮播图成功");
        }
        return Response.versionError("删除轮播图失败");
    }

    @GetMapping("getSlideImgList")
    @ApiOperation(value = "获取轮播图列表")
    public Response getSlideImgList() {

        List<SlideImg> rs= new ArrayList<>();
              rs=  iSlideImgService.getAllSlideImg();
        if (rs!=null){
            return Response.success("获取成功",rs);
        }
        return Response.versionError("获取失败");
    }

}

