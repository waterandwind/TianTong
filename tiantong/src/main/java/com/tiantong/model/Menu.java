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
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名字
     */
    @TableField("name")
    private String name;

    /**
     * 0停用1启用
     */
    @TableField("state")
    private Integer state;

    @TableField("code")
    private String code;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
