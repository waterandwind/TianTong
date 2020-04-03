package com.tiantong.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SlideWithMusicDto extends SlideImg {
    /**
     * 文件路径
     */
    private String profileUrl;

    /**
     * 歌名
     */
    private String name;

    private String singer;

    /**
     * 专辑
     */
    private String album;

    /**
     * 海报
     */
    private String posterUrl;

    /**
     * 歌曲时长
     */
    private Integer timeLength;

    private String lyricUrl;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createDate;

    /**
     * 审核状态：0 待审核；1审核通过；2 不通过
     */
    private Integer state;

    /**
     * 收藏数
     */
    private Integer collectNum;

    private Integer likeNum;

    /**
     * 歌曲图
     */
    private String songImg;

    /**
     * 播放数
     */
    private Integer playNum;


    /**
     * 播放数
     */
    private String remark;


    private Integer songerId;
}
