package org.noanamegroup.pawbox.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.noanamegroup.pawbox.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO extends BaseMapper<User>
{

}


