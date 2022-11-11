package com.xybert.springbootswagger.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xybert.springbootswagger.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xybert
 * @description UserMapper
 * @date 2022/11/10 18:25
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
