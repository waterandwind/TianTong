package com.tiantong.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lls
 * @since 2020-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("music")
public class Music extends Model<Music> {

    private static final long serialVersionUID=1L;

    /**
     * 音乐id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件路径
     */
    @TableField("profile_url")
    private String profileUrl;

    /**
     * 歌名
     */
    @TableField("name")
    private String name;

    @TableField("singer")
    private String singer;

    /**
     * 专辑
     */
    @TableField("album")
    private String album;

    /**
     * 海报
     */
    @TableField("poster_url")
    private String posterUrl;

    /**
     * 歌曲时长
     */
    @TableField("time_length")
    private Integer timeLength;

    @TableField("lyric_url")
    private String lyricUrl;

    /**
     * 创建者
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createDate;

    /**
     * 审核状态：0 待审核；1审核通过；2 不通过
     */
    @TableField("state")
    private Integer state;

    /**
     * 收藏数
     */
    @TableField("collect_num")
    private Integer collectNum;

    @TableField("like_num")
    private Integer likeNum;

    /**
     * 歌曲图
     */
    @TableField("song_img")
    private String songImg;

    /**
     * 播放数
     */
    @TableField("play_num")
    private Integer playNum;

    @TableField("songer_id")
    private Integer songerId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
