package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Student;
import util.SqlConnection;

public class StudentDao {

    private Connection con; // �������
    private PreparedStatement ps;   // �����ݿⷢ�����
    private ResultSet rs;   // ���շ��صĽṹ
    private List<Student> studentList;  // ����ִ�н��
    
    public StudentDao() {
        studentList = new ArrayList<>();
    }
    
    /**
     * �����û�,����������ֻ��Ͱ༶���
     */
    public void add(Student student) {
        con = SqlConnection.getConnection();
        String sql = "INSERT INTO student(name, birthday, mobile, clbumId, sex) VALUES(?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setDate(2, new Date(student.getBirthday().getTime()));
            ps.setString(3, student.getMobile());
            ps.setInt(4, student.getClbumId());
            ps.setInt(5, student.getSex());
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("��ӳɹ�~`");
            }
            SqlConnection.closeConnection(null, ps, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ���������û�
     */
    public List<Student> allQuery() {
        con = SqlConnection.getConnection();
        String sql = "SELECT id, name, sex, birthday ,mobile, clbumId FROM student ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            Student student = null;
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getInt("sex"));
                student.setBirthday(rs.getDate("birthday"));
                student.setMobile(rs.getString("mobile"));
                student.setClbumId(rs.getInt("clbumId"));
                studentList.add(student);
            }
            SqlConnection.closeConnection(rs, ps, con);
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ���ݱ�Ž��в����û�
     */
    public Student queryById(Integer id) {
        con = SqlConnection.getConnection();
        String sql = "SELECT id, name, sex, birthday ,mobile, clbumId FROM student WHERE id= ?";
        try {
            ps =con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Student student = null;
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getInt("sex"));
                student.setBirthday(rs.getDate("birthday"));
                student.setMobile(rs.getString("mobile"));
                student.setClbumId(rs.getInt("clbumId"));
            }
            SqlConnection.closeConnection(rs, ps, con);
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ���ݱ��ɾ���û�
     */
    public void delById(Integer id) {
        con = SqlConnection.getConnection();
        String sql = "DELETE FROM student WHERE id=" + id;
        try {
            ps = con.prepareStatement(sql);
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("ɾ���û�<" + id + ">�ɹ�");
            }
            SqlConnection.closeConnection(null, ps, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ���ݰ༶��Ų�ѯ�û�
     */
    public List<Student> queryByClbumId(Integer clbumId) {
        con = SqlConnection.getConnection();
        String sql = "SELECT id, name, sex, birthday, mobile, clbumId FROM student WHERE clbumId=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, clbumId);
            rs = ps.executeQuery();
            Student student = null;
            while (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSex(rs.getInt("sex"));
                student.setBirthday(rs.getDate("birthday"));
                student.setMobile(rs.getString("mobile"));
                student.setClbumId(rs.getInt("clbumId"));
                studentList.add(student);
            }
            SqlConnection.closeConnection(rs, ps, con);
            return studentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ���ݱ���޸��û�,������ֵΪ����
     */
    public void updateById(Student student) {
        con = SqlConnection.getConnection();
        String sql = "UPDATE student SET name=?, sex=?, birthday=?, mobile=?, clbumId=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getSex());
            ps.setDate(3, new Date(student.getBirthday().getTime()));
            ps.setString(4, student.getMobile());
            ps.setInt(5, student.getClbumId());
            ps.setInt(6, student.getId());
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("�޸��û���" + student.getId() + "���ɹ�");
            }
            SqlConnection.closeConnection(null, ps, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
