package com.tiantong.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("song_from_table")
public class SongFromTable extends Model<SongFromTable> {

    private static final long serialVersionUID=1L;

    /**
     * 歌单名
     */
    @TableId(value = "song_form_name", type = IdType.AUTO)
    private String songFormName;

    @TableField("account_id")
    private Integer accountId;

    @TableField("id")
    private Integer id;

    /**
     * 0不可删除，1可删除
     */
    @TableField("state")
    private Integer state;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private LocalDateTime createDate;


    @Override
    protected Serializable pkVal() {
        return this.songFormName;
    }

}
