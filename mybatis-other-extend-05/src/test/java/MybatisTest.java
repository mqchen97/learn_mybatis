import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.pojo.Employee;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
    public void test_01() {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

        PageHelper.startPage(1,2);

        List<Employee> employeeList = mapper.queryList();

        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        // 当前页
        List<Employee> list = pageInfo.getList();
        System.out.println("list = " + list);
        // 总页数
        int pages = pageInfo.getPages();
        System.out.println("pages = " + pages);
        // 总条数
        long total = pageInfo.getTotal();
        System.out.println("total = " + total);
        int pageNum = pageInfo.getPageNum();
        System.out.println("pageNum = " + pageNum);
        int pageSize = pageInfo.getPageSize();
        System.out.println("pageSize = " + pageSize);
    }


    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        session.commit();
        session.close();
    }
}
