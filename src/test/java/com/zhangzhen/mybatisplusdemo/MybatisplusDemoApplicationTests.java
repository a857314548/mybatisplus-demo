package com.zhangzhen.mybatisplusdemo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangzhen.mybatisplusdemo.entity.User;
import com.zhangzhen.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisplusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询所有
     */
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    /**
     * 查询单个
     */

    @Test
    public void insertOne() {
        User user = new User();
        user.setName("测试乐观锁");
        user.setAge(20);
        user.setEmail("2114059980@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("insert:" + insert);
    }

    /**
     * 修改单个
     */
    @Test
    public void update() {
        User user = new User();
        user.setId(1361279559005110274L);
        user.setAge(25);
        int num = userMapper.updateById(user);
        System.out.println("num:" + num);
    }

    /**
     * 测试乐观锁
     */
    @Test
    public void testOptimisticLocker() {
        //测试乐观锁成功 -------------------------------
        // 先查询
        /*User user = userMapper.selectById(1364195830105395201L);
        System.out.println(user);
        // 再修改
        user.setAge(30);

        int i = userMapper.updateById(user);
        System.out.println(i);*/

        //测试乐观锁失败 -------------------------------
        //先查询
        User user1 = userMapper.selectById(1364195830105395201L);
        System.out.println(user1);
        //修改 此时将bersion-1使版本号小于数据库版本号
        user1.setVersion(user1.getVersion() - 1);
        int i = userMapper.updateById(user1);
        System.out.println(i);
        //此时不会修改成功,无报错
    }

    /**
     * 通过id批量查询
     */
    @Test
    public void findByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        users.forEach(user ->{
            System.out.println(user);
        });
    }

    /**
     * 通过多个条件查询
     */
    @Test
    public void findByCondition() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","张小凡");
        map.put("age","18");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(user -> {
            System.out.println(user);
        });
    }

    /**
     * 分页查询
     */
    @Test
    public void findByPage(){
        Page<User> page = new Page<>(1,3);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getPages()); //总页数
        System.out.println(page.getRecords()); //数据列表
        System.out.println(page.getSize()); //总页数
        System.out.println(page.getTotal()); //总数量
        System.out.println(page.hasNext()); //是否有下一个数据
        System.out.println(page.hasPrevious()); //是否有上一个数据

    }

}
