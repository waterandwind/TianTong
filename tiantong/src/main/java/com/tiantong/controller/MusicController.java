package com.tiantong.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiantong.config.LrcAnalyze;
import com.tiantong.mapper.MusicMapper;
import com.tiantong.model.*;
import com.tiantong.service.IMusicService;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.connector.ClientAbortException;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    IMusicService iMusicService;
    @Autowired
    MusicMapper musicMapper;
    @PostMapping("addMusic")
    @ApiOperation(value = "添加歌曲")
    public Response addAccount(@RequestBody Music music) {
        music.setTimeLength(getMusicLength(music.getProfileUrl()));
        boolean rs=iMusicService.save(music);
        if (rs){
            return Response.success("歌曲添加成功");
        }
        return Response.versionError("添加歌曲失败");
    }
    @GetMapping("getMusicList")
    @ApiOperation(value = "获取歌曲列表")
    public Response getMenuList(QueryDto dto) {
        QueryWrapper<Music> qw=new QueryWrapper<>();
        qw.eq("state",dto.getState());
        if (dto.getKeyWord()!=null){
            qw.like("name",dto.getKeyWord());
        }

        IPage rs = iMusicService.page(new Page<Music>(dto.getCurrent(),dto.getPageSize()),qw);
        if (rs!=null){
            return Response.success("获取成功",rs);
        }else {
            return Response.bizError("获取失败");
        }
    }
    @GetMapping("getAllMusic")
    @ApiOperation(value = "获取所有歌曲")
    public Response getAllMusic() {

        List<Music> rs = iMusicService.getAllMusic();
        if (rs!=null){
            return Response.success("获取成功",rs);
        }else {
            return Response.bizError("获取失败");
        }
    }
    @PostMapping("batchEditMusic")
    @ApiOperation(value = "通过不通过歌曲")
    public Response batchEditMenu(@RequestBody BatchDto dto) {
        List<Music> musicList=new ArrayList<>();
        for (Integer id:
                dto.getIdList()) {
            Music tem=new Music();
            tem.setState(dto.getState());
            tem.setId(id);
            if (dto.getRemark()!=null){
                tem.setRemark(dto.getRemark());
            }
            musicList.add(tem);
        }
        boolean rs = iMusicService.updateBatchById(musicList);
        if (rs){
            return Response.success("歌曲修改完成");
        }else {
            return Response.bizError("歌曲修改失败");
        }
    }
    @PostMapping("singleEditMusic")
    @ApiOperation(value = "单首音乐通过不通过歌曲")
    public Response singleEditMusic(@RequestBody Music music) {

        boolean rs = iMusicService.updateById(music);
        if (rs){
            return Response.success("歌曲修改完成");
        }else {
            return Response.bizError("歌曲修改失败");
        }
    }
    @DeleteMapping("batchRemoveMusic")
    @ApiOperation(value = "批量删除歌曲")
    public Response batchRemoveMusic( List<Integer> idList) {
        boolean rs=iMusicService.removeByIds(idList);
        if (rs){
            return Response.success("歌曲删除成功");
        }
        return Response.versionError("歌曲删除失败");
    }
    @PostMapping("editMusic")
    @ApiOperation(value = "修改歌曲")
    public Response editMusic(@RequestBody Music music) {
        music.setTimeLength(getMusicLength(music.getProfileUrl()));
        boolean rs=iMusicService.updateById(music);
        if (rs){
            return Response.success("歌曲修改成功");
        }
        return Response.versionError("歌曲修改失败");
    }
    @GetMapping("getMusic")
    @ApiOperation(value = "获取单个歌曲集歌词")
    public Response getMusic( Integer musicId) {
        Music music= iMusicService.getById(musicId);
        if (music!=null){
            MusicDto dto=new MusicDto();
            BeanUtils.copyProperties(music,dto);
            LrcAnalyze lrcAnalyze = new LrcAnalyze(profilePath+"/"+music.getLyricUrl());
            dto.setLrc(lrcAnalyze.getLrcList());
            return Response.success("查找成功",dto);
        }
        return Response.notFound("未找到歌曲");
    }
    @GetMapping("addPlayNum")
    @ApiOperation(value = "增加播放数")
    public void addPlayNum( Integer musicId) {
        musicMapper.addPlayNum(musicId);
    }
    @GetMapping("addLikeNum")
    @ApiOperation(value = "增加点赞数")
    public void addLikeNum( Integer musicId) {
        musicMapper.addLikeNum(musicId);
    }
    @GetMapping("addCollectNum")
    @ApiOperation(value = "增加收藏数")
    public void addCollectNum( Integer musicId) {
        musicMapper.addCollectNum(musicId);
    }

    @GetMapping("search")
    @ApiOperation(value = "搜索歌手或歌曲")
    public Response search( SearchDto dto) {
        if (dto.getKeyWord()==null||dto.getKeyWord()==""){
            return Response.success("搜索完毕",new ArrayList<>());
        }
        if(dto.getType()==0){
            List<Music> list=iMusicService.searchMusicByName(dto.getKeyWord());
            return Response.success("搜索完毕",list);
        }else if (dto.getType()==1) {
            List<SingerInfo> list=iMusicService.searchSingerInfo(dto.getKeyWord());
            return Response.success("搜索完毕",list);
        }

        return Response.notFound("未找到歌曲");
    }
    @GetMapping("searchSingerAndMusic")
    @ApiOperation(value = "搜索歌手和歌曲")
    public Response searchSingerAndMusic( SearchDto dto) {
        if (dto.getKeyWord()==null||dto.getKeyWord()==""){
            return Response.success("搜索完毕",new ArrayList<>());
        }
            SearchSingerAndMusic rs=new SearchSingerAndMusic();
            List<Music> musicList=iMusicService.searchMusicByName(dto.getKeyWord());
            List<SingerInfo> singerList=iMusicService.searchSingerInfo(dto.getKeyWord());
            if (musicList==null){
                rs.setMusicList(new ArrayList<>());
            }else { rs.setMusicList(musicList);}
            if (singerList==null){
                rs.setSingerList(new ArrayList<>());
             }else {
                rs.setSingerList(singerList);
            }

            return Response.success("搜索完毕",rs);
    }
    @GetMapping("getSingerMusic")
    @ApiOperation(value = "获取歌手的歌曲")
    public Response getSingerMusic( Integer songerId,Integer flag) {
        List<Music> music= iMusicService.getSingerMusic(songerId,flag);
        if (music!=null){
            return Response.success("查找成功",music);
        }
        return Response.notFound("未找到歌曲");
    }
    @GetMapping("helloWorld")
    @ApiOperation(value = "测试")
    public Response firstPartyHome(String test) { LrcAnalyze lrcAnalyze = new LrcAnalyze("E:\\IdeaProject\\TianTong\\tiantong\\src\\main\\resources\\profile\\上原れな - 届かない恋 ’13.lrc");
        return Response.success("测试正常", lrcAnalyze.LrcGetList());
    }

    private static final String profilePath = System.getProperty("user.dir") + "/src/main/resources/profile";

//    private String profilePath = "C://MyWeb//tiantong//file";
    @GetMapping("testLength")
    @ApiOperation(value = "测试")
    public int getMusicLength(String musicName){
        File music = new File(profilePath + "/"+musicName);
        try {
            MP3File f = (MP3File) AudioFileIO.read(music);
            MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
           return audioHeader.getTrackLength();

        } catch(Exception e) {
            return -1;
        }
       }

    @RequestMapping("/play/{musicName:.+}")
    @ResponseBody
    public void wavStream(HttpServletRequest request, HttpServletResponse response,@PathVariable("musicName")String musicName) throws Exception {
        //文件目录
        File music = new File(profilePath + "/"+musicName);


        String range = request.getHeader("Range");

        //开始下载位置
        long startByte = 0;
        //结束下载位置
        long endByte = music.length() - 1;

        //有range的话
        if (range != null && range.contains("bytes=") && range.contains("-")) {
            range = range.substring(range.lastIndexOf("=") + 1).trim();
            String ranges[] = range.split("-");
            try {
                //判断range的类型
                if (ranges.length == 1) {
                    //类型一：bytes=-2343
                    if (range.startsWith("-")) {
                        endByte = Long.parseLong(ranges[0]);
                    }
                    //类型二：bytes=2343-
                    else if (range.endsWith("-")) {
                        startByte = Long.parseLong(ranges[0]);
                    }
                }
                //类型三：bytes=22-2343
                else if (ranges.length == 2) {
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }

            } catch (NumberFormatException e) {
                startByte = 0;
                endByte = music.length() - 1;
            }
        }

        //要下载的长度（为啥要加一问小学数学老师去）
        long contentLength = endByte - startByte + 1;
        //文件名
        String fileName = music.getName();
        //文件类型
        String contentType = request.getServletContext().getMimeType(fileName);


        //各种响应头设置
        //参考资料：https://www.ibm.com/developerworks/cn/java/joy-down/index.html
        //坑爹地方一：看代码
        response.setHeader("Accept-Ranges", "bytes");
        //坑爹地方二：http状态码要为206
        response.setStatus(206);
        response.setContentType(contentType);
        response.setHeader("Content-Type", contentType);
        //这里文件名换你想要的，inline表示浏览器直接实用（我方便测试用的）
        //参考资料：http://hw1287789687.iteye.com/blog/2188500
//        response.setHeader("Content-Disposition", "inline;filename=test.mp3");
        response.setHeader("Content-Length", String.valueOf(contentLength));
        //坑爹地方三：Content-Range，格式为
        // [要下载的开始位置]-[结束位置]/[文件总大小]
        response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + music.length());


        BufferedOutputStream outputStream = null;
        RandomAccessFile randomAccessFile = null;
        //已传送数据大小
        long transmitted = 0;
        try {
            randomAccessFile = new RandomAccessFile(music, "r");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startByte);
            //坑爹地方四：判断是否到了最后不足4096（buff的length）个byte这个逻辑（(transmitted + len) <= contentLength）要放前面！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
            //不然会会先读取randomAccessFile，造成后面读取位置出错，找了一天才发现问题所在
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
                //停一下，方便测试，用的时候删了就行了
//                Thread.sleep(10);
            }
            //处理不足buff.length部分
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }

            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();
            System.out.println("下载完毕：" + startByte + "-" + endByte + "：" + transmitted);

        } catch (ClientAbortException e) {
            System.out.println("用户停止下载：" + startByte + "-" + endByte + "：" + transmitted);
            //捕获此异常表示拥护停止下载
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
