package com.rentx.businessservices.interfaces;

import com.rentx.entities.User;

public interface IUserProfileService {
    /**
     * method to find user by id
     * @param userId user id
     * @return user id
     */
    User findUserById(int userId);

    /**
     * method to update user profile
     * @param userId user id
     * @param user user object
     */
    void updateUserProfile(int userId, User user);
}
