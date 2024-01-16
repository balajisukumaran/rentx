package com.rentx.businessservices;

import com.rentx.businessservices.exceptions.AppException;
import com.rentx.businessservices.interfaces.IUserService;
import com.rentx.config.Constant;
import com.rentx.dataaccess.interfaces.IUserDAO;
import com.rentx.dataaccess.repository.*;
import com.rentx.dtos.ResetPassword;
import com.rentx.entities.*;
import com.rentx.entities.enums.IdentifyBy;
import com.rentx.utils.UserServiceUtils;
import com.rentx.utils.UserServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rentx.dtos.CredentialsDto;
import com.rentx.dtos.SignUpDto;
import com.rentx.dtos.UserDto;
import com.rentx.mappers.UserMapper;
import lombok.RequiredArgsConstructor;

/**
 * User business services
 */
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    /**
     * private user DAO
     */
    private IUserDAO userDAO;

    /**
     * private Password Encoder
     */
    private PasswordEncoder passwordEncoder;

    /**
     * private User Repository
     */
    private UserRepository userRepository;

    /**
     * constructor for user business service
     *
     * @param userDAO user data access object
     */
    @Autowired
    public UserService(IUserDAO userDAO, UserRepository userRepository) {
        this.userDAO = userDAO;
        passwordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
    }

    /**
     * return all users
     *
     * @return List of user dtos
     */
    @Override
    public List<UserDto> findAll() {
        return UserMapper.toUserDtos(userDAO.findAll());
    }

    /**
     * add a new user
     *
     * @param userDto user object
     * @return added user
     */
    @Override
    @Transactional
    public UserDto insert(UserDto userDto) {
        User user = userDAO.insert(UserMapper.toUser(userDto));
        return UserMapper.toUserDto(user);
    }

    /**
     * authenticate a user
     *
     * @param credentialsDto credentials object
     * @return user object
     */
    @Override
    public UserDto login(CredentialsDto credentialsDto) {
        Optional<User> userFound = userDAO.findBy(credentialsDto.login(), IdentifyBy.Login);

        if (userFound != null && userFound.isPresent()) {
            User user = userFound.get();

            if (user.getIsExists() == 1) {
                if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),
                        user.getPassword())) {
                    return UserMapper.toUserDto(user);
                }
                throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);

            } else {
                throw new AppException("User is deleted !!", HttpStatus.BAD_REQUEST);
            }
        }

        throw new AppException("Unknown user", HttpStatus.NOT_FOUND);
    }

    /**
     * register user
     *
     * @param userDto user object
     * @return registered user
     */
    @Override
    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userDAO.findBy(userDto.login(), IdentifyBy.Login);

        if (optionalUser != null && optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = UserMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));
        user.setIsExists(1);

        User savedUser = userDAO.insert(user);

        return UserMapper.toUserDto(savedUser);
    }

    /**
     * reset password
     *
     * @param resetPassword reset password object
     * @return user object
     */
    @Override
    public UserDto reRegister(ResetPassword resetPassword) {
        User user = UserMapper.resetPasswordToUser(resetPassword);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(resetPassword.password())));
        User savedUser = userDAO.insert(user);
        return UserMapper.toUserDto(savedUser);
    }

    /**
     * find a user by login id
     *
     * @param login login id
     * @return user
     */
    @Override
    public UserDto findByLogin(String login) {
        Optional<User> user = userDAO.findBy(login, IdentifyBy.Login);
        if (user == null)
            return null;
        return user.map(UserMapper::toUserDto).orElse(null);
    }


    /**
     * find a user by email
     *
     * @param email user email
     * @return user object
     */
    @Override
    public UserDto findByEmail(String email) {
        Optional<User> user = userDAO.findBy(email, IdentifyBy.Email);

        if (user == null)
            return null;
        return user.map(UserMapper::toUserDto).orElse(null);
    }

    /**
     * find a user by reset token
     *
     * @param resetToken reset token
     * @return user object
     */
    @Override
    public UserDto findByResetToken(String resetToken) {
        Optional<User> user = userDAO.findBy(resetToken, IdentifyBy.ResetToken);

        if (user == null)
            return null;
        return user.map(UserMapper::toUserDto).orElse(null);
    }

    /**
     * method to verify user
     *
     * @param status string status
     * @param userID user id
     */
    @Override
    public void verifyUser(String status, int userID) {
        try {
            User user = userRepository.findByUserID(userID);
            if (user != null) {
                user.setStatus(status);
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * method to delete user
     *
     * @param userID user id
     */
    @Override
    public void deleteUser(int userID) {
        try {
            User user = userRepository.findByUserID(userID);
            if (user != null) {
                user.setIsExists(0);
                userRepository.save(user);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
