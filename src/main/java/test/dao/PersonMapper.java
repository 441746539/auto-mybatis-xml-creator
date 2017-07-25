package test.dao;

import my.demo.Person;
import my.demo.PersonDto;

import java.util.List;

/**
 * Created by qiang.su on 2017/7/24.
 */
public interface PersonMapper {

    public List<Person> list(PersonDto param);

    public int insert(PersonDto param);

    public int update(PersonDto param);

    public void delete(PersonDto param);

}
