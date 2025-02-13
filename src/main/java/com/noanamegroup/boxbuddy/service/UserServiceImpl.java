package com.noanamegroup.boxbuddy.service;

import com.noanamegroup.boxbuddy.entity.User;
import com.noanamegroup.boxbuddy.entity.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceImpl
{
    /**
     * Add a user
     *
     * @param user
     * @return
     */
    User addUser(UserDTO user);

    /**
     * Get a user
     *
     * @param userId
     * @return
     */
    User getUser(Integer userId);

    /**
     * Update a user
     *
     * @param user
     * @return
     */
    User updateUser(UserDTO user);
}
