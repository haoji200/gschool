package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.entity.MyUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * UserMapper
 *
 * @Author: wd
 * @CreateTime: 2020-04-03
 * @Description:
 */
@Repository
public interface UserMapper extends BaseMapper<MyUser> {
}