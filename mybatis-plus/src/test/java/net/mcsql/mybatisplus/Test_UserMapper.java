package net.mcsql.mybatisplus;

import net.mcsql.mybatisplus.entity.Product;
import net.mcsql.mybatisplus.entity.User;
import net.mcsql.mybatisplus.mapper.ProductMapper;
import net.mcsql.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class Test_UserMapper {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;

    @Test
    void contextLoads() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert(){

        User user = new User();
        user.setName("Helen01");
        user.setAge(18);
        user.setEmail("55317332@qq.com");

        int result = userMapper.insert(user);
        System.out.println("影响的行数：" + result); //影响的行数
        System.out.println("id：" + user); //id自动回填
    }

    @Test
    public void TestUpdate() {
        User user = new User();
        user.setId(1321352655997177857L);
        user.setAge(25);

        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    @Test
    public void testConcurrentUpdate() {

        //1、小李
        Product p1 = productMapper.selectById(0L);
        System.out.println("小李取出的价格：" + p1.getPrice());

        //2、小王
        Product p2 = productMapper.selectById(0L);
        System.out.println("小王取出的价格：" + p2.getPrice());

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        productMapper.updateById(p1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result = productMapper.updateById(p2);
//        if(result == 0){//更新失败，重试
//            //重新获取数据
//            p2 = productMapper.selectById(1L);
//            //更新
//            p2.setPrice(p2.getPrice() - 30);
//            productMapper.updateById(p2);
//        }

        //最后的结果
        Product p3 = productMapper.selectById(0L);
        System.out.println("最后的结果：" + p3.getPrice());
    }

    //通过多个id批量查询
    @Test
    public void testSelectBatchIds(){
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        userList.forEach(System.out::println);
    }

    //简单的条件查询
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
}
