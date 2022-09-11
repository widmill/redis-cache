package com.example.rediscache.controller;

import com.example.rediscache.exception.NoValueForThisKey;
import com.example.rediscache.model.ListValue;
import com.example.rediscache.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ListController {

    private final RedisService redisService;

    public ListController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/lget/{key}")
    public ResponseEntity getList(@PathVariable String key) {

        try {
            return ResponseEntity.ok(redisService.getList(key));
        } catch (NoValueForThisKey e) {
            return ResponseEntity.badRequest().body("Value for this key does not exist.");
        }

    }

    @PostMapping("/lset")
    public Map<String, List<String>> setList(@RequestBody ListValue listValue) {

        return redisService.setList(listValue);

    }
}
