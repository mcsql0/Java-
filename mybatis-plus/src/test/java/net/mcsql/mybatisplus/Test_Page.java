package net.mcsql.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.mcsql.mybatisplus.entity.User;
import net.mcsql.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class Test_Page {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelectPage() {

        Page<User> userPage = new Page<>(1, 5);
        Page<User> selectPage = userMapper.selectPage(userPage, null);

        long size = selectPage.getSize();
        long total = selectPage.getTotal();
    }

    @Test
    public void testSelectMapsPage() {
        //方法一 ：缺点由许多null
       // Page<User> userPage = new Page<>(1, 5);
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.select("name", "age");
//        Page<User> page = userMapper.selectPage(userPage, wrapper);

        //方法二使用map来储存
        Page<Map<String,Object>> userPage = new Page<>(1, 5);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("name", "age");
        Page<Map<String, Object>> mapPage = userMapper.selectMapsPage(userPage, wrapper);

        mapPage.getRecords().forEach(System.out::println);
    }
}
