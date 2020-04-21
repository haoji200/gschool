package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.MyUser;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        MyUser myUser = new MyUser();
//        int i = userMapper.deleteById(1246111851289255937L);
//        Map<String,Object> map = new HashMap<>();
//        map.put("create_time","2020-04-03 17:20:06");
//        map.put("name","大佬4");
//        List<MyUser> myUsers = userMapper.selectByMap(map);
//        List<MyUser> myUsers = userMapper.selectBatchIds(Arrays.asList(7, 8, 10));
//        MyUser myUser1 = userMapper.selectById(1246088934530764801L);
//        myUser.setId(1246088934530764801L);
//        myUser.setName("大佬9");
//        myUser.setAge(29);
//        myUser.setEmail("999@qq.com");
//        myUser1.setVersion(myUser1.getVersion()-1);
//        int insert = userMapper.insert(myUser);
//        int insert = userMapper.updateById(myUser1);
//        MyUser myUser = userMapper.selectById(1);
        List<MyUser> myUsers = userMapper.selectList(null);
//        System.out.println(myUsers);
//        System.out.println(myUser);
//        myUsers.forEach(System.out::println);
        System.out.println(myUsers);
    }

    @Test
    public void testPage(){
        Page<MyUser> myUserPage = new Page<>(2,3);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(myUserPage, null);
        mapIPage.getRecords().forEach(System.out::println);
//        userMapper.selectPage(myUserPage,null);
//        myUserPage.getRecords().forEach(System.out::println);
        System.out.println(myUserPage.getCurrent());
        System.out.println(myUserPage.getTotal());
    }

    @Test
    public void testWrapper(){
        QueryWrapper<MyUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name");
        List<MyUser> myUsers = userMapper.selectList(queryWrapper);
        myUsers.forEach(System.out::println);
//        UpdateWrapper<MyUser> updateWrapper = new UpdateWrapper<>();
//        MyUser myUser = new MyUser();
//        myUser.setName("大佬66");
//        updateWrapper.like("name","5").set("age",33).setSql("email='898@qq.com'");
//        userMapper.update(myUser,updateWrapper);
    }
}
