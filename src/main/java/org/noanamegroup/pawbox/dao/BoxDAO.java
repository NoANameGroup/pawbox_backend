package org.noanamegroup.pawbox.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.noanamegroup.pawbox.entity.Box;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface BoxDAO extends BaseMapper<Box>
{
    @Select("SELECT b.* FROM box b " +
            "INNER JOIN user_received_boxes urb ON b.boxId = urb.boxId " +
            "WHERE urb.userId = #{userId}")
    List<Box> getReceivedBoxesByUserId(@Param("userId") Integer userId);
}