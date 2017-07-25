package my;

import my.demo.Department;
import my.framework.core.CoreProcessor;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class App {

    public static void main(String[] args) {
        //System.out.println(CoreProcessor.doProcess("test.dao.PersonMapper", Person.class).toString());
        System.out.println(CoreProcessor.doProcess("test.dao.DepartmentMapper", Department.class).toString());
    }

}
