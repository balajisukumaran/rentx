package com.rentx.dataaccess.repository;

import com.rentx.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * user repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * find a user by login
     * @param login login id
     * @return user
     */
    Optional<User> findByLogin(String login);

    /**
     * find by user id method
     * @param userId user Id
     * @return object of user
     */
    User findByUserID(int userId);

    /**
     * query to update the user status
     * @param status status
     * @param id id
     * @return updated status of user
     */
    @Modifying
    @Query("update User usr set usr.status = :status where usr.userID = :id")
    int updateStatusOfUser(@Param("status") String status, @Param("id") int id);

    /**
     * query to update is exists in user table
     * @param userID user id
     * @return updated table of user
     */
    @Modifying
    @Query("UPDATE User u SET u.isExists = 1 WHERE u.userID = :userID")
    int markUserAsDeleted(@Param("userID") int userID);
}


