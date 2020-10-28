package net.mcsql.mybatisplus;

import net.mcsql.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class Test_Delete {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Tom");
        map.put("age", 12);

        int i = userMapper.deleteByMap(map);
    }
}
