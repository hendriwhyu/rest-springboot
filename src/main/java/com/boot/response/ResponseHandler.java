package com.boot.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("data", responseObj);
            map.put("message", message);
            map.put("status", true);

            return new ResponseEntity<Object>(map, status);
        } catch (Exception e) {
            // TODO: handle exception
            map.put("data", responseObj);
            map.put("message", e.getMessage());
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
