package test;


import java.util.List;

import org.junit.Test;

import dao.StudentDao;
import entity.Student;

public class StudentTest {

    private StudentDao studentDao = null;
    
    @Test
    public void add() {
        studentDao = new StudentDao();
        Student student = new Student();
        student.setName("Î÷Èö");
        student.setMobile("121345");
        student.setClbumId(3);
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp tt = new java.sql.Timestamp(date.getTime());
        student.setBirthday(tt);
        studentDao.add(student);
    }
    
//    @Test
    public void queryAll() {
        studentDao = new StudentDao();
        List<Student> studentList = studentDao.allQuery();
        if (studentList == null || studentList.isEmpty()) {
            System.out.println("ÄÚÈİÎª¿Õ");
            return;
        }
        for (Student student : studentList) {
            System.out.println("±àºÅ: " + student.getId() + " ĞÕÃû: " + student.getName() + " ÊÖ»úºÅ: " 
                + student.getMobile() + " °à¼¶±àºÅ:" + student.getClbumId());
        }
    }
    
//    @Test
    public void queryById() {
        studentDao = new StudentDao();
        Student student2 = studentDao.queryById(1);
        System.out.println("±àºÅ: " + student2.getId() + " ĞÕÃû: " + student2.getName() + "ÊÖ»úºÅ: " 
                + student2.getMobile() + " °à¼¶±àºÅ:" + student2.getClbumId());
    }
    
//    @Test
    public void delById() {
        studentDao = new StudentDao();
        studentDao.delById(3);
    }
    
//    @Test
    public void updateById() {
        studentDao = new StudentDao();
        Student student = new Student();
        student.setId(2);
        student.setName("Â¬Éª");
        student.setMobile("2333");
        student.setSex(1);
        student.setClbumId(3);
        studentDao.updateById(student);
    }

}
