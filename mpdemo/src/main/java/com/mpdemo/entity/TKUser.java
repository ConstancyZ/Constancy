package com.mpdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zyh
 * @since 2020-10-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_k_user")
public class TKUser extends Model<TKUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登录名，教师默认为电话号码
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 密言
     */
    private String salt;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 标签
     */
    private String tag;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 出生日期
     */
    private Date birthdate;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信表id
     */
    @TableField("weixin_user_tab_id")
    private Long weixinUserTabId;

    /**
     * 园所id
     */
    @TableField("kdgt_tab_id")
    private Long kdgtTabId;

    /**
     * 备注
     */
    private String remark;

    /**
     * -1:注销，非系统用户 ， 0：新增状态（未激活） ，1：正常状态（已激活），2：已锁定（登录失败次数过多）,3：待审核
     */
    private String state;

    /**
     * 创建人
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField("updater_id")
    private Long updaterId;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
