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
@TableName("account")
public class Account extends Model<Account> {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号id
     */
    @TableField("account")
    private String account;

    /**
     * 账号名字
     */
    @TableField("account_name")
    private String accountName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 账号类型:0 用户；1 歌手；2管理员
     */
    @TableField("type")
    private Integer type;

    /**
     * 头像地址
     */
    @TableField("photo_url")
    private String photoUrl;

    /**
     * 禁用状态 0未禁用1 禁用
     */
    @TableField("state")
    private Integer state;

    @TableField("sex")
    private Integer sex;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
