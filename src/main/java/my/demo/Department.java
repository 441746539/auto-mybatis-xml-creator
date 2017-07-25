package my.demo;

import my.framework.annotation.CollectionColumn;
import my.framework.annotation.DBColumn;
import my.framework.annotation.PrimaryId;
import my.framework.annotation.TableInfo;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
@TableInfo(tableName = "department", parameterType = "DepartmentDto", resultMap = "DepartmentMap")
public class Department {

    @PrimaryId
    private String id;
    @DBColumn
    private String dname;
    @CollectionColumn(itemClass = Person.class, foreignKeyColunm = "id", relColumnInforeignTable = "department_id")
    private List<Person> persons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
