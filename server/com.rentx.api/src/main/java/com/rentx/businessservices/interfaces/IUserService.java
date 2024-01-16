package com.rentx.businessservices.interfaces;

import com.rentx.dtos.CredentialsDto;
import com.rentx.dtos.ResetPassword;
import com.rentx.dtos.SignUpDto;
import com.rentx.dtos.UserDto;

import java.util.List;

import com.rentx.entities.ProductReal;

/**
 * Interface for user business services
 */
public interface IUserService {

    /**
     * return all users
     * @return List of user dtos
     */
    List<UserDto> findAll();

    /**
     * add a new user
     * @param userDto user object
     * @return added user
     */
    UserDto insert(UserDto userDto);

    /**
     * authenticate a user
     * @param credentialsDto credentials object
     * @return user object
     */
    UserDto login(CredentialsDto credentialsDto);

    /**
     * register user
     * @param userDto user object
     * @return registered user
     */
    UserDto register(SignUpDto userDto);

    /**
     * reset password
     * @param resetPassword reset password object
     * @return user object
     */
    UserDto reRegister(ResetPassword resetPassword);

    /**
     * find a user by login id
     * @param login login id
     * @return user
     */
    UserDto findByLogin(String login);

    /**
     * find a user by email id
     * @param email email id
     * @return user
     */
    UserDto findByEmail(String email);

    /**
     * find a user by reset token
     * @param resetToken reset token
     * @return user
     */
    UserDto findByResetToken(String resetToken);

    /**
     * Verifies the user
     * @param userID user id
     * @return user user object
     */
    void verifyUser(String status, int userID);

    /**
     * method to delete user
     * @param userID user id
     */
    void deleteUser(int userID);
}
