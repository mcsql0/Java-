package net.mcsql.servlet;

import net.mcsql.bean.Employee;
import net.mcsql.mapping.EmployeeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

//@CacheConfig(cacheNames = "emp")    //公共的Cecha 配置
@Service
public class EmployeeServlet {

    @Autowired
    public EmployeeMapping employeeMapping;

    @Cacheable(cacheNames = "emp", key = "#id")
    public Employee getEmployee(Integer id) {
        System.out.println("查询员工："+id);
        return employeeMapping.getEmployee(id);
    }


    /**
     * 测试自定义Cache Key
     */
//    @Cacheable(cacheNames = "emp", key = "myKeyGenerator")
//    public Employee getEmployee(Integer id) {
//        System.out.println("查询员工："+id);
//        return employeeMapping.getEmployee(id);
//    }

    /**
     * 测试 @CachePut.
     */
    @CachePut(value = "emp", key = "#{employee.id}")
    public void updataEmployee(Employee employee) {
        employeeMapping.updataEmployee(employee);
    }

    /**
     * @Caching 多配置注解
     */
//    @Caching(
//        put = {
//                @CachePut(key = "#id")
//        },
//        evict = {
//                @CacheEvict(key = "#id")
//        }
//    )
    public void delEmployee(Integer id){
        employeeMapping.delEmployee(id);
    }
}
