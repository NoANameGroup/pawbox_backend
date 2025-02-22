package org.noanamegroup.pawbox.service;

import org.noanamegroup.pawbox.entity.Box;
import org.noanamegroup.pawbox.entity.User;
import org.noanamegroup.pawbox.entity.dto.BoxDTO;
import org.noanamegroup.pawbox.entity.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceImpl
{
    /**
     * Register a user
     *
     * @param userDTO
     * @return
     */
    User register(UserDTO userDTO);

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

    /**
     * User login
     *
     * @param email
     * @param password
     * @return
     */
    User login(String email, String password);

    /**
     * Send a box
     *
     * @param boxDTO
     * @param senderId
     * @return
     */
    Box sendBox(BoxDTO boxDTO, Integer senderId);

    /**
     * Receive a box
     *
     * @param boxId
     * @param receiverId
     * @return
     */
    Box receiveBox(Integer boxId, Integer receiverId);

    /**
     * Find a user by email
     *
     * @param email
     * @return
     */
    User findByEmail(String email);
}
