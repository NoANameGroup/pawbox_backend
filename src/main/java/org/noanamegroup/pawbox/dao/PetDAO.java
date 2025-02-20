package org.noanamegroup.pawbox.dao;

import org.apache.ibatis.annotations.Mapper;
import org.noanamegroup.pawbox.entity.Pet;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface PetDAO extends BaseMapper<Pet> {
} 