package test.dao;

import my.demo.Department;
import my.demo.DepartmentDto;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/25.
 */
public interface DepartmentMapper {

    public List<Department> list(DepartmentDto param);

    public int insert(DepartmentDto param);

    public int update(DepartmentDto param);

    public void delete(DepartmentDto param);
}
