package org.noanamegroup.pawbox.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.noanamegroup.pawbox.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO extends BaseMapper<User>
{
    @Select("SELECT userId, username, password, email FROM user WHERE userId = #{userId}")
    User selectById(@Param("userId") Integer userId);
}


