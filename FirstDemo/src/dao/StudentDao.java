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

    private Connection con; // 获得连接
    private PreparedStatement ps;   // 向数据库发送语句
    private ResultSet rs;   // 接收返回的结构
    private List<Student> studentList;  // 保存执行结果
    
    public StudentDao() {
        studentList = new ArrayList<>();
    }
    
    /**
     * 增加用户,添加姓名、手机和班级编号
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
                System.out.println("添加成功~`");
            }
            SqlConnection.closeConnection(null, ps, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 查找所有用户
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
     * 根据编号进行查找用户
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
     * 根据编号删除用户
     */
    public void delById(Integer id) {
        con = SqlConnection.getConnection();
        String sql = "DELETE FROM student WHERE id=" + id;
        try {
            ps = con.prepareStatement(sql);
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("删除用户<" + id + ">成功");
            }
            SqlConnection.closeConnection(null, ps, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 根据班级编号查询用户
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
     * 根据编号修改用户,更改数值为姓名
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
                System.out.println("修改用户＜" + student.getId() + "＞成功");
            }
            SqlConnection.closeConnection(null, ps, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
