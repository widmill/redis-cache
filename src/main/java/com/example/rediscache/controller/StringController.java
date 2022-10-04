package com.example.rediscache.controller;

import com.example.rediscache.exception.NoValueForThisKey;
import com.example.rediscache.model.StringValue;
import com.example.rediscache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StringController {

    private final RedisService redisService;

    @Autowired
    public StringController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/get/{key}")
    public String getString(@PathVariable String key) {

        return redisService.getString(key);

    }

    @PostMapping("/set")
    public Map<String, String> setString(@RequestBody StringValue stringValue) {

        return redisService.setString(stringValue);

    }

    @DeleteMapping("/del/{key}")
    public ResponseEntity deleteString(@PathVariable String key) {

        try {
            return ResponseEntity.ok(redisService.deleteString(key));
        } catch (NoValueForThisKey e) {
            return ResponseEntity.badRequest().body("Value for this key does not exist.");
        }
    }

    @GetMapping("/keys/{string}")
    public List<String> getKeys(@PathVariable String string) {

        return redisService.getKeys(string);
    }

}
