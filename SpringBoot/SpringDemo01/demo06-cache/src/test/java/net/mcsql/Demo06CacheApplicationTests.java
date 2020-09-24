package net.mcsql;

import net.mcsql.bean.Employee;
import net.mcsql.mapping.DepartmentMapping;
import net.mcsql.mapping.EmployeeMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.naming.Name;

@SpringBootTest
class Demo06CacheApplicationTests {

	@Autowired
	private EmployeeMapping employeeMapping;

	@Test
	void contextLoads() {

		System.out.println(employeeMapping.getEmployee(1));
	}

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/**
	 * 自定义Redis序列化Json
	 */
	@Autowired
	RedisTemplate<Object, Employee> redisTemplate;

//	@Autowired
//	RedisTemplate redisTemplate;

	@Test
	public void Test_StringRedisTemplate() {
		System.out.println(stringRedisTemplate.opsForValue().get("name"));
	}

	/**
	 * 测试储存对象，测试redisTemplate
	 */
	@Test
	public void Test_RedisTemplate() {
//		redisTemplate.opsForValue().set("emp-1",employeeMapping.getEmployee(2));  //添加员工Emp对象
		System.out.println(redisTemplate.opsForValue().get("emp-1"));			  //序列化
	}
}
