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
@TableName("song_form_info_table")
public class FormInfo extends Model<FormInfo> {

    private static final long serialVersionUID=1L;

    /**
     * 歌单名
     */
    @TableField(value = "song_form_name")
    private String songFormName;

    @TableField("account_id")
    private Integer accountId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 0不可删除，1可删除
     */
    @TableField("state")
    private Integer state;

    /**
     * 创建时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
