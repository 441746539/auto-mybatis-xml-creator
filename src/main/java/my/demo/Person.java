package my.demo;

import my.framework.annotation.*;

/**
 * Created by qiang.su on 2017/7/24.
 */
@TableInfo(tableName = "person", parameterType = "PersonDto", resultMap = "PersonMap")
public class Person {

    @PrimaryId
    private String id;
    @DBColumn
    private String name;
    @DBColumn("department_id")
    private String departmentId;
    @DBColumn("create_time")
    @DateType(pattern = "%Y-%m-%d")
    private String createTime;

    @AssosiationColumn(foreignKeyColunm = "department_id", relColumnInforeignTable = "id")
    private Department department;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
