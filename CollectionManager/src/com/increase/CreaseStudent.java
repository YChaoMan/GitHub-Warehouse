package com.increase;
/**
 * ����ʵ��������Ž�ѧ������
 */
import java.util.Map;

import com.modern.StudentInformation;

public class CreaseStudent {

    private static CreaseStudent create;
    
    public void createStudent(String id, StudentInformation student,
        Map<String,StudentInformation> studentMap) {
        studentMap.put(id, student);    // ���������ͽ������콣��������ȥ
    }
    
    public static CreaseStudent getInterface() {
        if (create == null) {
            create =  new CreaseStudent();
        }
        return create;
    }
}
