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
@TableName("songer")
public class Songer extends Model<Songer> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("account_id")
    private Integer accountId;


    /**
     * 歌手简介
     */
    @TableField("info")
    private String info;

    /**
     * 歌手类型
     */
    @TableField("singer_type")
    private String singerType;

    /**
     * 审核状态
     */
    @TableField("state")
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
