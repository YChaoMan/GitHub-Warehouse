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
        student.setName("����");
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
            System.out.println("����Ϊ��");
            return;
        }
        for (Student student : studentList) {
            System.out.println("���: " + student.getId() + " ����: " + student.getName() + " �ֻ���: " 
                + student.getMobile() + " �༶���:" + student.getClbumId());
        }
    }
    
//    @Test
    public void queryById() {
        studentDao = new StudentDao();
        Student student2 = studentDao.queryById(1);
        System.out.println("���: " + student2.getId() + " ����: " + student2.getName() + "�ֻ���: " 
                + student2.getMobile() + " �༶���:" + student2.getClbumId());
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
        student.setName("¬ɪ");
        student.setMobile("2333");
        student.setSex(1);
        student.setClbumId(3);
        studentDao.updateById(student);
    }

}
