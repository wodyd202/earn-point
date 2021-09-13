package com.ljy.earnpoint.core.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class APIResponse extends ResponseEntity<HashMap<String, Object>> {
    public APIResponse(Object body, HttpStatus status) {
        super(createBody(body, status), status);
    }

    private static HashMap<String, Object> createBody(Object body, HttpStatus status){
        HashMap<String,Object> result = new HashMap<>();
        if(status.is3xxRedirection() || status.is2xxSuccessful()){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        if(body != null){
            result.put("response", body);
        }
        return result;
    }
}
