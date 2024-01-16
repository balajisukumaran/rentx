package com.rentx.mappers;

import com.rentx.dtos.ResetPassword;
import com.rentx.dtos.SignUpDto;
import com.rentx.dtos.UserDto;
import com.rentx.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    /**
     * static user dto method
     * @param user user object
     * @return updated user dto
     */
    public static UserDto toUserDto(User user) {
        UserDto userDto = null;

        if (user != null) {
            userDto = new UserDto();
            userDto.setUserID(user.getUserID());
            userDto.setPhone(user.getPhone());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setPrefLanguage(user.getPrefLanguage());
            userDto.setJoiningDate(user.getJoiningDate());
            userDto.setPrivacy(user.getPrivacy());
            userDto.setStatus(user.getStatus());
            userDto.setAreaId(user.getAreaId());
            userDto.setLogin(user.getLogin());
            userDto.setEmail(user.getEmail());
            userDto.setResetToken(user.getResetToken());
            userDto.setToken(user.getPassword());
            userDto.setIsexists(user.getIsExists() == 1);
        }
        return userDto;
    }

    /**
     * static list of user dto
     * @param users object
     * @return list of object dto
     */
    public static List<UserDto> toUserDtos(List<User> users) {
        List<UserDto> userDtos = null;

        if (users != null) {
            userDtos = new ArrayList<>();

            for (var user : users)
                userDtos.add(toUserDto(user));
        }

        return userDtos;
    }

    /**
     * static to user
     * @param userDto object
     * @return updated user dto
     */
    public static User toUser(UserDto userDto) {
        User user = null;

        if (userDto != null) {
            user = new User();
            user.setUserID(userDto.getUserID());
            user.setPhone(userDto.getPhone());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPrefLanguage(userDto.getPrefLanguage());
            user.setJoiningDate(userDto.getJoiningDate());
            user.setPrivacy(userDto.getPrivacy());
            user.setStatus(userDto.getStatus());
            user.setAreaId(userDto.getAreaId());
            user.setLogin(userDto.getLogin());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getToken());
            user.setResetToken(userDto.getResetToken());
        }

        return user;
    }

    /**
     * method to users
     * @param userDtos object
     * @return array list user dto
     */
    public static List<User> toUsers(List<UserDto> userDtos) {
        List<User> users = null;

        if (userDtos != null) {
            users = new ArrayList<>();

            for (var userDto : userDtos)
                users.add(toUser(userDto));
        }

        return users;
    }

    /**
     * method to sign up to user
     * @param signUpDto object
     * @return updaetd user obejct
     */
    public static User signUpToUser(SignUpDto signUpDto) {
        User user = null;

        if (signUpDto != null) {
            user = new User();
            user.setPhone(signUpDto.phone());
            user.setFirstName(signUpDto.firstName());
            user.setLastName(signUpDto.lastName());
            user.setPrefLanguage(signUpDto.prefLanguage());
            user.setJoiningDate(signUpDto.joiningDate());
            user.setPrivacy(signUpDto.privacy());
            user.setAreaId(signUpDto.areaId());
            user.setStatus(signUpDto.status());
            user.setLogin(signUpDto.login());
            user.setEmail(signUpDto.email());
            user.setPassword(signUpDto.password().toString());
        }

        return user;
    }

    /**
     * method to reset password to user
     * @param resetPassword object
     * @return user object updated
     */
    public static User resetPasswordToUser(ResetPassword resetPassword) {
        User user = null;

        if (resetPassword != null) {
            user = new User();
            user.setUserID(resetPassword.userId());
            user.setPhone(resetPassword.phone());
            user.setFirstName(resetPassword.firstName());
            user.setLastName(resetPassword.lastName());
            user.setPrefLanguage(resetPassword.prefLanguage());
            user.setJoiningDate(resetPassword.joiningDate());
            user.setPrivacy(resetPassword.privacy());
            user.setAreaId(resetPassword.areaId());
            user.setStatus(resetPassword.status());
            user.setLogin(resetPassword.login());
            user.setEmail(resetPassword.email());
            user.setResetToken(resetPassword.resetToken());
        }

        return user;
    }
}
