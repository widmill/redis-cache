package com.example.rediscache.model;

import java.util.List;
import java.util.Map;

public class HashValue {

    private String key;

    private Map<String, String> value;

    public HashValue(String key, Map<String, String> value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
    }
}
