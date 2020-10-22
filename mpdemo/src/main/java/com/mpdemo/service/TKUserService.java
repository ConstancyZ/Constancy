package com.mpdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mpdemo.entity.TKUser;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zyh
 * @since 2020-10-20
 */
public interface TKUserService extends IService<TKUser> {

    TKUser selectUserList(Long id);
}
