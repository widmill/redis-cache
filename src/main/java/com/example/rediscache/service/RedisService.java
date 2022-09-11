package com.example.rediscache.service;

import com.example.rediscache.exception.NoValueForThisKey;
import com.example.rediscache.model.HashValue;
import com.example.rediscache.model.ListValue;
import com.example.rediscache.model.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class RedisService {

    Logger log = LoggerFactory.getLogger(RedisService.class);

    private final HashMap<String, Object> cacheMemory = new HashMap<>();


    public String getString(String key) {

        log.info("Looking for value of the key: [key = {}]", key);

        if (cacheMemory.get(key) == null) {
            throw new NoValueForThisKey("Value for this key does not exist.");
        }

        return (String) cacheMemory.get(key);
    }

    public Map<String, String> setString(StringValue object) {

        log.info("Putting value for the key: [key = {}, value = {}]", object.getKey(), object.getValue());

        if (cacheMemory.get(object.getKey()) != null) {
            log.warn("Replacing new value for the key = {}", object.getKey());
        }

        cacheMemory.put(object.getKey(), object.getValue());

        return Map.of(object.getKey(), object.getValue());

    }

    public Map<String, String> delString(String key) {

        log.info("Deleting value for the key: [key = {}]", key);

        if (cacheMemory.get(key) == null) {
            throw new NoValueForThisKey("Value for this key does not exist.");
        }

        Map<String, String> deletedMap = Map.of(key, (String) cacheMemory.get(key));

        cacheMemory.remove(key);

        return deletedMap;

    }

    public List<String> getList(String key) {

        log.info("Looking for list value of the key: [key = {}]", key);

        if (cacheMemory.get(key) == null) {
            throw new NoValueForThisKey("Value for this key does not exist.");
        }

        List<Object> list = List.of(cacheMemory.get(key));

        return list.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

    }

    public Map<String, List<String>> setList(ListValue object) {

        log.info("Putting list value for the key: [key = {}, value = {}]", object.getKey(), object.getValue());

        if (cacheMemory.get(object.getKey()) != null) {
            log.warn("Replacing new value for the key = {}", object.getKey());
        }

        cacheMemory.put(object.getKey(), object.getValue());

        return Map.of(object.getKey(), object.getValue());
    }

    public String getHash(String key, String field) {

        log.info("Looking for value of the key and field: [key = {}, field = {}]", key, field);

        Object raw = cacheMemory.get(key);

        if (raw == null) {
            throw new NoValueForThisKey("Value for this key does not exist.");
        }

        @SuppressWarnings("unchecked")
        HashMap<String, String> map = (HashMap<String, String>) raw;

        return map.get(field);

    }

    public Map<String, HashMap<String, String>> setHash(HashValue object) {

        log.info("Putting hash value for the key: [key = {}, value = {}]", object.getKey(), object.getValue());

        if (cacheMemory.get(object.getKey()) != null) {
            log.warn("Replacing new value for the key = {}", object.getKey());
        }

        cacheMemory.put(object.getKey(), object.getValue());

        HashMap<String, String> map = new HashMap<>();


        for (String key : object.getValue().keySet()) {
            map.put(key, object.getValue().get(key));
        }

        return Map.of(object.getKey(), map);
    }

    public List<String> getKeys(String string) {

        log.info("Looking for keys with string in [string = {}]", string);

        List<String> keys = new ArrayList<>();

        for (String key : cacheMemory.keySet()) {
            if (key.contains(string)) {

                keys.add(key);

            }
        }
        return keys;
    }
}
