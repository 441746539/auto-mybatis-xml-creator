package test;

import my.demo.Department;
import my.demo.DepartmentDto;
import my.demo.Person;
import my.demo.PersonDto;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.dao.DepartmentMapper;
import test.dao.PersonMapper;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration(locations={"classpath:context/applicationContext.xml"})
public class Test {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    @org.junit.Test
    public void list(){
        PersonDto param = new PersonDto();
        param.setCreateTimeBegin("2017-09-07");
        param.setCreateTimeEnd("2017-09-12");
        //param.setId("1");
        List<Person> list = personMapper.list(param);
        System.out.println(list.size());
    }

    @org.junit.Test
    public void listDept(){
        DepartmentDto param = new DepartmentDto();
        List<Department> list = departmentMapper.list(param);
        System.out.println(list.size());
    }

    @org.junit.Test
    public void insert(){
        PersonDto param = new PersonDto();
        param.setName("AAASB");
        param.setDepartmentId("2");
        int i =personMapper.insert(param);
        System.out.println(i);
    }

    @org.junit.Test
    public void update(){
        PersonDto param = new PersonDto();
        param.setId("1");
        param.setName("Changed");
        int i =personMapper.update(param);
        System.out.println(i);
    }

    @org.junit.Test
    public void delete(){
        PersonDto param = new PersonDto();
        param.setId("4");
        personMapper.delete(param);
    }

}
