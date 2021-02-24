package com.zhangzhen.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
     * 创建单个
     */

    @Test
    public void insertOne() {
        User user = new User();
        user.setName("测试mybatisplus性能2");
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

    /**
     * 测试单条删除
     */
    @Test
    public void testDelete() {
        int i = userMapper.deleteById(1364559288407764994L);
        System.out.println("删除条数:" + i);
    }

    /**
     * 测试批量删除
     */
    @Test
    public void testBatchDelete() {
        int i = userMapper.deleteBatchIds(Arrays.asList(2L, 3L));
        System.out.println("批量删除条数：" + i);
    }

    /**
     * 测试逻辑删除
     */
    @Test
    public void testLoginDelete() {
    }

    /**
     * 测试条件删除
     */
    @Test
    public void testConditionDelete() {
        Map<String,Object> map = new HashMap<>();

        map.put("name","Billie");
        map.put("age","24");

        int i = userMapper.deleteByMap(map);
        System.out.println("条件删除条数:" + i);
    }

    /**
     * 测试复杂查询
     */
    @Test
    public void testComplexConditionQuery() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //ge gt le lt isNull isNotNull 大于等于 大于 小于等于 小于 为null 不为null
        //queryWrapper.ge("age",24);
        //queryWrapper.gt("age",24);
        //queryWrapper.le("age",24);
        //queryWrapper.lt("age",24);
        //queryWrapper.isNull("create_time");
        //queryWrapper.isNotNull("create_time");

        //eq、ne 等于 不等于
        //queryWrapper.eq("name","测试乐观锁");
        //queryWrapper.ne("name","测试乐观锁");

        //between、notBetween  某某范围之间,不在某某范围之间
        //queryWrapper.between("age",21,24);
        //queryWrapper.notBetween("age",21,24);

        //allEq 根据多个字段查询
        /*Map<String,Object> map = new HashMap<>();
        map.put("id",4L);
        map.put("name","Sandy");
        map.put("age",21);
        queryWrapper.allEq(map);*/

        //like、notLike、likeLeft、likeRight 模糊匹配 模糊不匹配 左边模糊匹配 右边模糊匹配
        //queryWrapper.like("name","张");
        //queryWrapper.notLike("name","张");
        //queryWrapper.likeLeft("name","y");
        //queryWrapper.likeRight("name","S");

        // in、notIn、inSql、notinSql、exists、notExists  在指定范围 不在指定范围 子查询 非子查询 存在 不存在
        //queryWrapper.in("name","测试张小凡","测试逻辑删除");
        //queryWrapper.notIn("name","测试张小凡","测试逻辑删除");
        //SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0
        // AND age IN ( select age from user where age >= 21)
        //queryWrapper.inSql("age","select age from user where age >= 21 ");
        //queryWrapper.notInSql("age","select age from user where age >= 21 ");
        //存在则根据前面条件全查出来 不存在则一条也不查
        //queryWrapper.notExists("select age from user where age = 18");

        //or、and 或者 和 and 这里用updateWapper
        /*UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("age",20).or().likeLeft("name","锁");
        List<User> users1 = userMapper.selectList(userUpdateWrapper);*/

        //orderByDesc、orderByAsc  降序 升序
        //queryWrapper.orderByDesc("id");
        //queryWrapper.orderByAsc("id");

        //last 直接拼接到 sql 的最后 limit 3 前三条 limit 1,3 从第4条开始往后查3条
        //注意：只能调用一次,多次调用以最后一次为准 有sql注入的风险,请谨慎使用
        //queryWrapper.last("limit 3");
        //queryWrapper.last("limit 2,3");

        //指定要查询的列
        //queryWrapper.select("id","name","age");

        //set、setSql 最终的sql会合并 user.setAge()，以及 userUpdateWrapper.set() 和 setSql() 中 的字段 这里使用updateWapper
        /*User usera = new User();
        usera.setAge(27);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","Sandy").set("name","jhon").setSql("email = 'zwx746910@qq.com'");

        userMapper.update(usera,updateWrapper);*/

        List<User> users = userMapper.selectList(queryWrapper);
        //System.out.println(users);
        users.forEach(user -> {
            System.out.println(user + "\t");
        });
    }

}
