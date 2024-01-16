package com.rentx.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;

public class MessagesControllerTest {
    /**
     * test case to test messages from message controller
     */
    @Test
    public void TestMessages(){
        MessagesController messagesController = new MessagesController();
        ResponseEntity<List<String>> res = messagesController.messages();
        assertEquals(HttpStatus.OK,res.getStatusCode());
        List<String> responseBody = res.getBody();
        assertTrue(responseBody != null && responseBody.contains("first") && responseBody.contains("second"));
    }
}
