package com.xybert.springbooteasyexcel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybert.springbooteasyexcel.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xybert
 * @description UserMapper
 * @date 2022/12/23 14:21
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
