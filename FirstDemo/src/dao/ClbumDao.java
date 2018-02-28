package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Clbum;
import entity.Student;
import util.SqlConnection;

public class ClbumDao {
    
    private List<Clbum> clbumDaoList = null;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Clbum clbum = null;
    private Student student;
    private List<Student> studentList = null;
    
    public ClbumDao() {
        clbumDaoList = new ArrayList<>();
    }

    /**
     * ȫ����ѯ
     */
    public List<Clbum> allQuery() {
        con = SqlConnection.getConnection();
        String sql = "SELECT c.clbumId,c.clbumName FROM clbum c;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                clbum = new Clbum();
                clbum.setClbumId(rs.getInt("c.clbumId"));
                clbum.setClbumName(rs.getString("c.clbumName"));
                clbumDaoList.add(clbum);
            }
            SqlConnection.closeConnection(rs, ps, con);
            return clbumDaoList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ȫ����ѯ
     */
    public List<Student> allQueryByLike(String condition) {
        con = SqlConnection.getConnection();
        StringBuffer sql = new StringBuffer();
        studentList = new ArrayList<>();
        sql.append("SELECT id, name, sex, birthday, mobile, clbumId FROM student"); // ���û������ݣ���ô����Ĭ�ϵĲ�ѯ
        try {
            if (condition.matches("[0-9]+")) {  // �жϲ�ѯ����Ϊ����
            sql.append(" WHERE id LIKE ? OR sex LIKE ?");
            sql.append(" OR mobile LIKE ? OR clbumId LIKE ?;");
            ps = con.prepareStatement(sql.toString());
            ps.setInt(1, Integer.valueOf(condition));
            ps.setInt(2, Integer.valueOf(condition));
            ps.setString(3, condition);
            ps.setInt(4, Integer.valueOf(condition));
            } else {    // ��ѯ����Ϊ����
                Integer sexId = 3;
                sql.append(" WHERE name LIKE ? OR sex LIKE ?;");
                ps = con.prepareStatement(sql.toString());
                ps.setString(1, "%" + condition + "%");
                if ("��".equals(condition)) {
                    sexId = 1;
                    ps.setInt(2, sexId);
                }
                if ("Ů".equals(condition)) {
                    sexId = 0;
                    ps.setInt(2, sexId);
                }
                ps.setInt(2, sexId);
            }
            rs = ps.executeQuery();
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
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ����id��ѯ�༶
     */
    public Clbum queryById(Integer id) {
        con = SqlConnection.getConnection();
        String sql = "SELECT c.clbumId,c.clbumName FROM clbum c WHERE c.clbumId= ?;";
        try {
            ps =con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                clbum = new Clbum();
                clbum.setClbumId(rs.getInt("c.clbumId"));
                clbum.setClbumName(rs.getString("c.clbumName"));
            }
            SqlConnection.closeConnection(rs, ps, con);
            return clbum;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ���ݰ༶���Ʋ�ѯ�༶
     */
    public Clbum queryByName(String clbumName) {
        con = SqlConnection.getConnection();
        String sql = "SELECT c.clbumId,c.clbumName FROM clbum c WHERE c.clbumName= ?;";
        try {
            ps =con.prepareStatement(sql);
            ps.setString(1, clbumName);
            rs = ps.executeQuery();
            while (rs.next()) {
                clbum = new Clbum();
                clbum.setClbumId(rs.getInt("c.clbumId"));
                clbum.setClbumName(rs.getString("c.clbumName"));
            }
            SqlConnection.closeConnection(rs, ps, con);
            return clbum;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * ���Ӱ༶
     */
    public void addClbum(String clbumName) {
        con = SqlConnection.getConnection();
        String sql = "INSERT INTO clbum(clbumName) VALUES(?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, clbumName);
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
     * ���ݱ���޸��û�
     */
    public void updateById(Clbum clbum) {
        con = SqlConnection.getConnection();
        String sql = "UPDATE clbum SET clbumName=? WHERE clbumId=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, clbum.getClbumName());
            ps.setInt(2, clbum.getClbumId());
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("�޸İ༶��" + clbum.getClbumId() + "���ɹ�");
            }
            SqlConnection.closeConnection(null, ps, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ���ݰ༶���ɾ���༶
     */
    public void delById(Integer clbumId) {
        con = SqlConnection.getConnection();
        String sql = "DELETE FROM clbum WHERE clbumId=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, clbumId);
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("ɾ���༶��" + clbumId + "���ɹ�");
            }
            SqlConnection.closeConnection(null, ps, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
