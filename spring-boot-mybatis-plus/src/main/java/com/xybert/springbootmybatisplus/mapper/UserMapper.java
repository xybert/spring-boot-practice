package com.xybert.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybert.springbootmybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xybert
 * @description UserMapper
 * @date 2022/10/24 18:18
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
