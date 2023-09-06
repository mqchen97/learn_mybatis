import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.mapper.TeacherMapper;
import com.atguigu.mapper.UserMapper;
import com.atguigu.pojo.Employee;
import com.atguigu.pojo.Teacher;
import com.atguigu.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private SqlSession session;
    // junit会在每一个@Test方法前执行@BeforeEach方法

    @BeforeEach
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder()
                .build(
                        Resources.getResourceAsStream("mybatis-config.xml"))
                .openSession();
    }
    @Test
    public void test() throws IOException {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employee = new Employee();
        employee.setEmpName("Wussy");
        employee.setEmpSalary(3333.11);
        System.out.println(employee.getEmpId());
        int row = mapper.insertEmp(employee);
        System.out.println("row = " + row);
        System.out.println(employee.getEmpId());
    }

    @Test
    public void test_02() throws IOException {
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        Teacher teacher = new Teacher();
        teacher.settName("billy");
        int row = teacherMapper.insertTeacher(teacher);
        System.out.println("row = " + row);
    }

    @Test
    public void test_03(){
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = userMapper.queryAll();
        System.out.println("users = " + users);
    }

    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        session.commit();
        session.close();
    }
}
