package net.mcsql.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.mcsql.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
