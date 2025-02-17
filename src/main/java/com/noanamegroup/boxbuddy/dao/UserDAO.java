package com.noanamegroup.boxbuddy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.noanamegroup.boxbuddy.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO extends BaseMapper<User>
{

}


