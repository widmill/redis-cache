package com.example.rediscache.service;

import com.example.rediscache.exception.NoValueForThisKey;
import com.example.rediscache.model.HashValue;
import com.example.rediscache.model.ListValue;
import com.example.rediscache.model.StringValue;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class RedisServiceTest {

    @InjectMocks
    private RedisService redisService;


    @Test
    public void setStringTest() {


        StringValue object = new StringValue("mykey", "hello");

        redisService.setString(object);

        Assertions.assertThat(redisService.getString("mykey")).isEqualTo(object.getValue());

    }

    @Test(expected = NoValueForThisKey.class)
    public void deleteStringTest()  {

        StringValue object = new StringValue("mykey", "hello");

        redisService.setString(object);

        redisService.delString(object.getKey());

        redisService.getString("mykey");

    }

    @Test
    public void getAllSuccessTest() {

        StringValue stringObject = new StringValue("mykey", "hello");


        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("from test");
        ListValue listObject = new ListValue("mykey1", list);

        Map<String, String> map = new HashMap<>();
        map.put("field1", "value1");
        map.put("field2", "value2");
        HashValue hashObject = new HashValue("mykey2", map);

        redisService.setString(stringObject);
        redisService.setList(listObject);
        redisService.setHash(hashObject);

        Assertions.assertThat(redisService.getString("mykey")).isEqualTo(stringObject.getValue());
//        Assertions.assertThat(redisService.getList("mykey1")).isEqualTo(listObject.getValue());
        Assertions.assertThat(redisService.getHash("mykey2", "field1")).isEqualTo(hashObject.getValue().get("field1"));
    }




}

