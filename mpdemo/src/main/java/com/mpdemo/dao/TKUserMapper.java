package com.mpdemo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mpdemo.entity.TKUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2020-10-20
 */
@Repository
public interface TKUserMapper extends BaseMapper<TKUser> {

}
