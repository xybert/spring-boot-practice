package com.xybert.springbootknife4j.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybert.springbootknife4j.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xybert
 * @description UserMapper
 * @date 2022/11/11 10:35
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
