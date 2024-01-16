package com.rentx.dataaccess;

import com.rentx.entities.User;
import com.rentx.entities.enums.IdentifyBy;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    /**
     * entity manager object
     */
    EntityManager entityManager;
    /**
     * user DAO class
     */
    UserDAO userDAO;
    /**
     * list of users object
     */
    List<User> users;

    /**
     * test method for user DAO
     */
    public UserDAOTest(){
        entityManager = Mockito.mock(EntityManager.class);
        userDAO = new UserDAO(entityManager);

        users = new ArrayList<>();
        User user1 =new User();
        user1.setEmail("a");
        user1.setLogin("b");
        user1.setResetToken("c");
        user1.setIsExists(1);
        User user2 =new User();
        user2.setEmail("d");
        user2.setLogin("e");
        user2.setResetToken("f");
        user2.setIsExists(1);
        users.add(user1);
        users.add(user2);

    }


    /**
     * test case for find all user dao
     */
    @Test
    void findAll() {
        TypedQuery<User> query = (TypedQuery<User>) Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("from User", User.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(users);

        var users = userDAO.findAll();

        assertNotNull(users);
        assertEquals(users.size(),2);
    }


    /**
     * test case for the insert method
     */
    @Test
    void insert() {
        Mockito.when(entityManager.merge(new Object())).thenReturn(null);
        var user = userDAO.insert(new User());
        assertNotNull(user);
    }


    /**
     * test case for the find by email from user
     */
    @Test
    void findByEmail() {
        TypedQuery<User> query = (TypedQuery<User>) Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("from User", User.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(users);
        var genUser=userDAO.findBy("a", IdentifyBy.Email);

        assertNotNull(genUser);
    }


    /**
     * test case to find by login
     */
    @Test
    void findByLogin() {
        TypedQuery<User> query = (TypedQuery<User>) Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("from User", User.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(users);
        var genUser=userDAO.findBy("b", IdentifyBy.Login);

        assertNotNull(genUser);
    }


    /**
     * test case for the find by reset token
     */
    @Test
    void findByResetToken() {
        TypedQuery<User> query = (TypedQuery<User>) Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("from User", User.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(users);
        var genUser=userDAO.findBy("c", IdentifyBy.ResetToken);

        assertNotNull(genUser);
    }


    /**
     * test case for the find by null
     */
    @Test
    void findByNull() {
        TypedQuery<User> query = (TypedQuery<User>) Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery("from User", User.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(null);
        var genUser=userDAO.findBy("c", IdentifyBy.ResetToken);
        assertNull(genUser);
    }
}