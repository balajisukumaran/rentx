package com.rentx.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;

public class ServerHealthControllerTest {
    /**
     * test case for healthz method
     */
    @Test
    public void testHealthz(){
        ServerHealthController serverHealthController = new ServerHealthController();
        ResponseEntity<String> res = serverHealthController.healthz();
        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals("Healthy",res.getBody());
    }
}
