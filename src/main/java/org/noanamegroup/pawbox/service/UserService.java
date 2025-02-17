package org.noanamegroup.pawbox.service;

import org.noanamegroup.pawbox.dao.UserDAO;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImpl
{
    @Autowired
    UserDAO userDAO;

    @Override
    public User signUp(UserDTO userDTO)
    {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userDAO.insert(user);
        return user;
    }

    @Override
    public User getUser(Integer userId) {
        return userDAO.selectById(userId);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User user = userDAO.selectById(userDTO.getUserId());
        if (user == null) {
            return null;
        }
        BeanUtils.copyProperties(userDTO, user);
        userDAO.updateById(user);
        return user;
    }

    @Override
    public User deleteUser(Integer userId)
    {
        User user = userDAO.selectById(userId);
        if (user == null) {
            return null;
        }
        userDAO.deleteById(userId);
        return user;
    }
}