package net.mcsql.mapping;

import net.mcsql.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Mapper
public interface EmployeeMapping {

    @Select("select * from employee where id = #{id}")
    public Employee getEmployee(Integer id);

    @Update("update employee set id = #{id}, lastName = #{lastName}, email = #{email}, gender = #{gender} , d_id = #{dId}")
    public void updataEmployee(Employee employee);

    @Delete("delete from employee where id = #{id}")
    public void delEmployee(Integer id);
}
