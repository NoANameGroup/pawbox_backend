package org.noanamegroup.pawbox.service;

import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceImpl
{
    /**
     * Sign up a user
     *
     * @param userDTO
     * @return
     */
    User signUp(UserDTO userDTO);

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
     * @param userDTO
     * @return
     */

    User updateUser(UserDTO userDTO);

    /**
     * Delete a user
     *
     * @param userId
     * @return
     */
    User deleteUser(Integer userId);
}
