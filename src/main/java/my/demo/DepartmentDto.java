package my.demo;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class DepartmentDto {

    private String id;
    private String dname;
    private List<PersonDto> persons;

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

    public void setPersons(List<PersonDto> persons) {
        this.persons = persons;
    }
}
