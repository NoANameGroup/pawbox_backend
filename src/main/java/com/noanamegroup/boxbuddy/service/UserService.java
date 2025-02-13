package com.noanamegroup.boxbuddy.service;

import com.noanamegroup.boxbuddy.dao.UserDAO;
import com.noanamegroup.boxbuddy.entity.User;
import com.noanamegroup.boxbuddy.entity.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl
{
    @Autowired
    UserDAO userDAO;

    @Override
    public User addUser(UserDTO user)
    {
        User userEntity = new User();
        // Copy properties from user to userEntity
        BeanUtils.copyProperties(user, userEntity);
        return userDAO.save(userEntity);
    }

    @Override
    public User getUser(Integer userId)
    {
       return userDAO.findById(userId).orElseThrow(() ->
        {
            throw new IllegalArgumentException("用户不存在，参数异常！");
        });
    }

    @Override
    public User updateUser(UserDTO user)
    {
        User userEntity = new User();
        // Copy properties from user to userEntity
        BeanUtils.copyProperties(user, userEntity);
        return userDAO.save(userEntity);
    }

    @Override
    public void deleteUser(Integer userId)
    {
        userDAO.deleteById(userId);
    }
}
