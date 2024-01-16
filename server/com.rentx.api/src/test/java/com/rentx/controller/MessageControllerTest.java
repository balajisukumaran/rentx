package com.rentx.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageControllerTest {
    @InjectMocks
    private MessagesController messagesController;

    @Test
    void testMessagesEndpoint() {
        // Arrange
        MockitoAnnotations.openMocks(this); // Initializes fields annotated with @Mock or @InjectMocks

        // Act
        ResponseEntity<List<String>> result = messagesController.messages();

        // Assert
        assertEquals(Arrays.asList("first", "second"), result.getBody());
        assertEquals(200, result.getStatusCodeValue()); // Assuming OK status code
    }
}
