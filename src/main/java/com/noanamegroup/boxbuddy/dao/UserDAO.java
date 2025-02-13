package com.noanamegroup.boxbuddy.dao;

import com.noanamegroup.boxbuddy.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User, Integer>
{

}
