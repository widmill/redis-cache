package com.example.rediscache.controller;


import com.example.rediscache.exception.NoValueForThisKey;
import com.example.rediscache.model.HashValue;
import com.example.rediscache.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HashController {

    private final RedisService redisService;

    public HashController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/hget/{key}/{field}")
    public String getHash(@PathVariable String key, @PathVariable String field) {

        return redisService.getHash(key, field);
    }

    @PostMapping("/hset")
    public Map<String, HashMap<String, String>> setHash(@RequestBody HashValue hashValue) {

        return redisService.setHash(hashValue);
    }


}
