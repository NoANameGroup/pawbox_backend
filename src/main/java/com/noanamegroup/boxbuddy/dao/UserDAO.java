package com.noanamegroup.boxbuddy.dao;

import com.noanamegroup.boxbuddy.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // 用对的，刚才看错了
public interface UserDAO extends CrudRepository<User, Integer>
{

}
