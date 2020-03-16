package com.tiantong.controller;

import com.tiantong.config.LrcAnalyze;
import com.tiantong.mapper.MusicMapper;
import com.tiantong.model.Music;
import com.tiantong.model.MusicDto;
import com.tiantong.model.Response;
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

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    IMusicService iMusicService;
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
    @GetMapping("getMusic")
    @ApiOperation(value = "添加歌曲")
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

    @GetMapping("helloWorld")
    @ApiOperation(value = "测试")
    public Response firstPartyHome(String test) { LrcAnalyze lrcAnalyze = new LrcAnalyze("E:\\IdeaProject\\TianTong\\tiantong\\src\\main\\resources\\profile\\上原れな - 届かない恋 ’13.lrc");
        return Response.success("测试正常", lrcAnalyze.LrcGetList());
    }



    private String profilePath = System.getProperty("user.dir") + "/src/main/resources/profile/";
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

    @RequestMapping("/wav")
    @ResponseBody
    public void wavStream(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //文件目录
        File music = new File(profilePath + "/届かない恋 .mp3");


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
