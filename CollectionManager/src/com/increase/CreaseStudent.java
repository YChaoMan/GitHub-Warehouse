package com.increase;
/**
 * 集中实例化对象放进学生类中
 */
import java.util.Map;

import com.modern.StudentInformation;

public class CreaseStudent {

    private static CreaseStudent create;
    
    public void createStudent(String id, StudentInformation student,
        Map<String,StudentInformation> studentMap) {
        studentMap.put(id, student);    // 符合条件就讲内容天剑到集合中去
    }
    
    public static CreaseStudent getInterface() {
        if (create == null) {
            create =  new CreaseStudent();
        }
        return create;
    }
}
