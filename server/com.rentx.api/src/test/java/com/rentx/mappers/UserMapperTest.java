package com.rentx.mappers;

import com.rentx.dtos.ResetPassword;
import com.rentx.dtos.SignUpDto;
import com.rentx.dtos.UserDto;
import com.rentx.entities.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    /**
     * user object
     */
    User user = new User();
    /**
     * user DTO obejct init
     */
    UserDto userDto = new UserDto();
    /**
     * sign up DTO object
     */
    SignUpDto signUpDto;
    /**
     * reset passsword object
     */
    ResetPassword resetPassword;

    /**
     * test method for user mapper
     */
    public UserMapperTest() {

        //user setup
        user = new User();
        user.setUserID(123);
        user.setEmail("test@gmail.com");
        user.setResetToken("testreset");
        user.setAreaId(123);
        user.setLogin("test-login");
        user.setPassword("password");
        user.setLastName("last");
        user.setFirstName("first");
        user.setJoiningDate(new Date(2020,2,2));
        user.setPrefLanguage("t");
        user.setPrivacy("privacy");
        user.setStatus("test");
        user.setPhone("12344232");

        //UserDto setup
        userDto = new UserDto();
        userDto.setUserID(123);
        userDto.setEmail("test@gmail.com");
        userDto.setResetToken("testreset");
        userDto.setAreaId(123);
        userDto.setLogin("test-login");
        userDto.setToken("asdas");
        userDto.setLastName("last");
        userDto.setFirstName("first");
        userDto.setJoiningDate(new Date(2020,2,2));
        userDto.setPrefLanguage("t");
        userDto.setPrivacy("privacy");
        userDto.setStatus("test");
        userDto.setPhone("12344232");

        //signUpDto setup
        signUpDto = new SignUpDto("first"
                , "last"
                , "1234534"
                ,"t"
                ,new Date(2020,2,2)
                ,"privacy"
                ,"asd"
                ,123
                ,"b@g.c"
                ,"login"
                ,new String("password").toCharArray());

        //ResetPassword setup
        resetPassword = new ResetPassword(123123
                ,"first"
                ,"last"
                ,"1213123"
                ,"t"
                ,new Date(2020,2,2)
                ,"privacy"
                ,"st"
                ,123
                ,"as@dsaf.c"
                ,"asdas"
                ,new String("asdasd").toCharArray()
                ,"asdasdas");
    }


    /**
     * test case to validate the user
     */
    @Test
    void toUserValidUser() {
        var generatedUser = UserMapper.toUser(userDto);
        assertEquals(generatedUser.getUserID(),userDto.getUserID());
        assertEquals(generatedUser.getPhone(),userDto.getPhone());
        assertEquals(generatedUser.getFirstName(),userDto.getFirstName());
        assertEquals(generatedUser.getLastName(),userDto.getLastName());
        assertEquals(generatedUser.getPrefLanguage(),userDto.getPrefLanguage());
        assertEquals(generatedUser.getJoiningDate(),userDto.getJoiningDate());
        assertEquals(generatedUser.getPrivacy(),userDto.getPrivacy());
        assertEquals(generatedUser.getStatus(),userDto.getStatus());
        assertEquals(generatedUser.getAreaId(),userDto.getAreaId());
        assertEquals(generatedUser.getLogin(),userDto.getLogin());
        assertEquals(generatedUser.getEmail(),userDto.getEmail());
        assertEquals(generatedUser.getResetToken(),userDto.getResetToken());
    }


    /**
     * test case to check null user entry
     */
    @Test
    void toUserNull() {
        var generatedUser = UserMapper.toUser(null);
        assertNull(generatedUser);
    }

    /**
     * test case to validate the user
     */
    @Test
    void toUsersValid() {
        List<UserDto> userDtos=new ArrayList<>();
        userDtos.add(new UserDto());
        userDtos.add(new UserDto());

        var generatedUsers= UserMapper.toUsers(userDtos);
        assertEquals(generatedUsers.size(),userDtos.size());
    }


    /**
     * test case for null user
     */
    @Test
    void toUsersNull() {
        var generatedUsers= UserMapper.toUsers(null);
        assertNull(generatedUsers);
    }


    /**
     * test case to validate user with DTO
     */
    @Test
    void toUserDtoValidUser() {
        var generatedUserDto = UserMapper.toUserDto(user);
        assertEquals(generatedUserDto.getUserID(),user.getUserID());
        assertEquals(generatedUserDto.getPhone(),user.getPhone());
        assertEquals(generatedUserDto.getFirstName(),user.getFirstName());
        assertEquals(generatedUserDto.getLastName(),user.getLastName());
        assertEquals(generatedUserDto.getPrefLanguage(),user.getPrefLanguage());
        assertEquals(generatedUserDto.getJoiningDate(),user.getJoiningDate());
        assertEquals(generatedUserDto.getPrivacy(),user.getPrivacy());
        assertEquals(generatedUserDto.getStatus(),user.getStatus());
        assertEquals(generatedUserDto.getAreaId(),user.getAreaId());
        assertEquals(generatedUserDto.getLogin(),user.getLogin());
        assertEquals(generatedUserDto.getEmail(),user.getEmail());
        assertEquals(generatedUserDto.getResetToken(),user.getResetToken());
        assertEquals(generatedUserDto.getToken(),user.getPassword());
    }


    /**
     * test case for user DTO to null
     */
    @Test
    void toUserDtoNull() {
        var generatedUserDto = UserMapper.toUserDto(null);
        assertNull(generatedUserDto);
    }


    /**
     * test case to validate the  user DTO
     */
    @Test
    void toUserDtosValid() {
        List<User> users=new ArrayList<>();
        users.add(new User());
        users.add(new User());
        var generatedUserDtos= UserMapper.toUserDtos(users);
        assertEquals(generatedUserDtos.size(),users.size());
    }


    /**
     * Null user DTO test case
     */
    @Test
    void toUserDtosNull() {
        var generatedUserDtos= UserMapper.toUserDtos(null);
        assertNull(generatedUserDtos);
    }

    /**
     * test case to validate the sign up user
     */
    @Test
    void signUpToUserValid() {
        var generatedUser = UserMapper.signUpToUser(signUpDto);
        assertEquals(generatedUser.getPhone(),signUpDto.phone());
        assertEquals(generatedUser.getFirstName(),signUpDto.firstName());
        assertEquals(generatedUser.getLastName(),signUpDto.lastName());
        assertEquals(generatedUser.getPrefLanguage(),signUpDto.prefLanguage());
        assertEquals(generatedUser.getJoiningDate(),signUpDto.joiningDate());
        assertEquals(generatedUser.getPrivacy(),signUpDto.privacy());
        assertEquals(generatedUser.getAreaId(),signUpDto.areaId());
        assertEquals(generatedUser.getStatus(),signUpDto.status());
        assertEquals(generatedUser.getLogin(),signUpDto.login());
        assertEquals(generatedUser.getEmail(),signUpDto.email());
        assertEquals(generatedUser.getPassword(),signUpDto.password().toString());
    }


    /**
     * test case for the signup for null user
     */
    @Test
    void signUpToUserNull() {
        var generatedUser = UserMapper.signUpToUser(null);
        assertNull(generatedUser);
    }


    /**
     * test case to reset password for user
     */
    @Test
    void resetPasswordToUser() {
    }


    /**
     * test case to reset password for user
     */
    @Test
    void resetPasswordToUserValid() {
        var generatedUser = UserMapper.resetPasswordToUser(resetPassword);

        assertEquals(generatedUser.getUserID(),resetPassword.userId());
        assertEquals(generatedUser.getPhone(),resetPassword.phone());
        assertEquals(generatedUser.getFirstName(),resetPassword.firstName());
        assertEquals(generatedUser.getLastName(),resetPassword.lastName());
        assertEquals(generatedUser.getPrefLanguage(),resetPassword.prefLanguage());
        assertEquals(generatedUser.getJoiningDate(),resetPassword.joiningDate());
        assertEquals(generatedUser.getPrivacy(),resetPassword.privacy());
        assertEquals(generatedUser.getAreaId(),resetPassword.areaId());
        assertEquals(generatedUser.getStatus(),resetPassword.status());
        assertEquals(generatedUser.getLogin(),resetPassword.login());
        assertEquals(generatedUser.getEmail(),resetPassword.email());
        assertEquals(generatedUser.getResetToken(),resetPassword.resetToken().toString());
    }


    /**
     * test case for the null user to reset password
     */
    @Test
    void resetPasswordToUserNull() {
        var generatedUser = UserMapper.resetPasswordToUser(null);
        assertNull(generatedUser);
    }
}