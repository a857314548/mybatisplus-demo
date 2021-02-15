package com.zhangzhen.mybatisplusdemo;

import com.zhangzhen.mybatisplusdemo.entity.User;
import com.zhangzhen.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisplusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void insertOne() {
        User user = new User();
        user.setName("测试张小凡");
        user.setAge(20);
        user.setEmail("2114059980@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("insert:" + insert);
    }

    @Test
    public void update() {
        User user = new User();
        user.setId(1361279559005110274L);
        user.setAge(25);
        int num = userMapper.updateById(user);
        System.out.println("num:" + num);
    }

}
