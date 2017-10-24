package my;

import my.bean.*;
import my.demo.Person;
import my.framework.core.CoreProcessor;

/**
 * Created by qiang.su on 2017/7/24.
 */
public class App {

    public static void main(String[] args) {
        //System.out.println(CoreProcessor.doProcess("test.dao.PersonMapper", Person.class).toString());
        //System.out.println(CoreProcessor.doProcess("test.dao.DepartmentMapper", Department.class).toString());
        System.out.println(CoreProcessor.doProcess("t", CollegeClassroomLesson.class).toString());
    }

}


//interface方法如下：
//   /**
//     * list
//     * @param param
//     * @param pagination
//     * @return
//     */
//    List<MaintenaceInfo> list(MaintenaceInfoDto param,Pagination pagination);
//    List<MaintenaceInfo> list(MaintenaceInfoDto param);
//    List<MaintenaceInfo> listNoRelate(MaintenaceInfoDto param,Pagination pagination);
//    List<MaintenaceInfo> listNoRelate(MaintenaceInfoDto param);
//
//    /**
//     * add
//     * @param param
//     * @return
//     */
//    int insert(MaintenaceInfoDto param);
//
//    /**
//     * update
//     * @param param
//     * @return
//     */
//    int update(MaintenaceInfoDto param);
//
//    /**
//     * delet
//     * @param param
//     * @return
//     */
//    int delete(MaintenaceInfoDto param);
//
//    /**
//     * queryone
//     * @param param
//     * @return
//     */
//    MaintenaceInfo queryOne(MaintenaceInfoDto param);
//    MaintenaceInfo queryOneNoRelate(MaintenaceInfoDto param);