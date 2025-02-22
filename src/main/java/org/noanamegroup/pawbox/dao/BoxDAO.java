package org.noanamegroup.pawbox.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.noanamegroup.pawbox.entity.Box;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface BoxDAO extends BaseMapper<Box>
{
    @Select("SELECT b.* FROM box b " +
            "INNER JOIN user_received_boxes urb ON b.boxId = urb.box_id " +
            "WHERE urb.user_id = #{userId}")
    List<Box> getReceivedBoxesByUserId(@Param("userId") Integer userId);

    @Insert("INSERT INTO user_received_boxes (user_id, box_id) VALUES (#{userId}, #{boxId})")
    void insertUserReceivedBox(@Param("userId") Integer userId, @Param("boxId") Integer boxId);
}