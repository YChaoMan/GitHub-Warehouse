package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import dao.ClbumDao;
import dao.StudentDao;
import entity.Clbum;
import entity.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private List<Student> studentList = null; // 保存学生的集合
    private List<Clbum> clbumList = null;   // 班级集合
    private StudentDao studentDao = null; // 对数据库进行操作
    private Student student = null; // 学生对象
    private ClbumDao clbumDao = null;   // 班级操作
    private Clbum clbum = null; // 班级实体
    private Map<String, Object> map = null; // 向页面传递集合

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        PrintWriter out = response.getWriter();
        System.out.println("studentServlet>> type>> " + type);
        Integer count = 1;
        if (!type.matches("[0-9]+")) { // 没选择的值则退出
            type = "1";
            response.sendRedirect("/view/grade/fail.jsp");
        } else {
            count = Integer.valueOf(type);
        }
        if (count > 6) {
            response.sendRedirect("/view/grade/fail.jsp");
        }
        Integer id = 0;
        String name = null;
        String maps = null;
        switch (type) {
        case "1": // 全部查询
            studentDao = new StudentDao();
            clbumDao = new ClbumDao();
            String query = request.getParameter("query");
            studentList = clbumDao.allQueryByLike(query); // 查询的默认类型为全部,若存在查询类型跳到QueryServlet中
            clbumList = clbumDao.allQuery();    // 查询出全部的班级信息，用以对比信息
            if (studentList == null || studentList.isEmpty()) {
                response.sendRedirect("/view/grade/fail.jsp");  // 查找条件不满足，进行弹出警告
                return;
            }
            if (clbumList == null || clbumList.isEmpty()) {
                response.sendRedirect("/view/grade/fail.jsp");
                return;
            }
            map = new HashMap<>();
            map.put("students", studentList);
            map.put("clbums", clbumList);
            maps = JSON.toJSONString(map);
            out.write(maps);
            break;
        case "2": // 新增
            studentDao = new StudentDao();
            student = new Student();
            StringBuffer nameBuffer = new StringBuffer();
            StringBuffer mobileBuffer = new StringBuffer();
            String[] nameArray = request.getParameter("username").split(" ");
            String[] mobileArray = request.getParameter("mobile").split(" ");
            for (int i = 0; i < nameArray.length; i++) {
                nameBuffer.append(nameArray[i]);
            }
            for (int i = 0; i < mobileArray.length; i++) {
                mobileBuffer.append(mobileArray[i]);
            }
            String nameParamter = nameBuffer.toString();
            String mobileParamter = mobileBuffer.toString();
            if (nameParamter == null || nameParamter.isEmpty() || "".equals(nameParamter)) {
                request.getRequestDispatcher("/view/grade/fail.jsp").forward(request, response);
            } else {
                student.setName(nameParamter); // 设置姓名
            }
            if (!mobileParamter.matches("[0-9]+")) {
                if (mobileParamter == null || mobileParamter.isEmpty()) {
                    student.setMobile("");
                }
                request.getRequestDispatcher("/view/grade/fail.jsp").forward(request, response);
            } else {
                student.setMobile(mobileParamter); // 设置手机号
            }
            String clbumIdParamter = request.getParameter("clbumId");
            System.out.println("studentServlet>> clbumId>> " + clbumIdParamter);
            boolean clbumIdVerify = clbumIdParamter.matches("[0-9]+");
            if (!clbumIdVerify) { // 如果不是数字
                if ("".equals(clbumIdVerify)) {
                    student.setClbumId(0);  // 设置班级编号
                }
                request.getRequestDispatcher("/view/grade/fail.jsp").forward(request, response);
            } else {
                Integer clbumId = Integer.valueOf(request.getParameter("clbumId"));
                System.out.println("StudentServlet>> clbumId>> " + clbumId);
                student.setClbumId(clbumId);
            }
            String sexId = request.getParameter("sex"); // 性别获取
            String birthday = request.getParameter("birthday");
            Date date = setDateFormat(birthday);
            student.setBirthday(date);
            student.setSex(Integer.valueOf(sexId));
            if (nameParamter != null && !"".equals(nameParamter)) {
                studentDao.add(student);
            }
            out.write(student.getName());
            break;
        case "3": // 按条件查询
            studentDao = new StudentDao();
            System.out.println("studentServlet>> id>> " + request.getParameter("id"));
            id = Integer.valueOf(request.getParameter("id"));
            student = studentDao.queryById(id);
            clbumDao = new ClbumDao();
            clbum = clbumDao.queryById(student.getClbumId());
            if (student == null) {
                request.getRequestDispatcher("/view/grade/fail.jsp").forward(request, response);
            }
            map = new HashMap<>();
            map.put("student", student);
            map.put("clbum", clbum);
            maps = JSON.toJSONString(map);
            out.write(maps);
            break;
        case "4": // 按条件删除
            studentDao = new StudentDao();
            clbumDao = new ClbumDao();
            studentDao.delById(Integer.valueOf(request.getParameter("id")));
            studentList = studentDao.allQuery();
            clbumList = clbumDao.allQuery();
            response.sendRedirect("/FirstDemo/view/grade/student_list.jsp");
            break;
        case "5": // 修改
            studentDao = new StudentDao();
            clbumDao = new ClbumDao();
            name = request.getParameter("userName");
            String sex =  request.getParameter("sex");
            String userMobily = request.getParameter("userMobily");
            String clbumName = request.getParameter("clbumName");
            birthday = request.getParameter("birthday");
            clbum = clbumDao.queryByName(clbumName);
            date = setDateFormat(birthday);
            student = new Student();
            id = Integer.valueOf(request.getParameter("id"));
            student.setId(id);
            student.setName(name);
            student.setSex(Integer.valueOf(sex));
            student.setClbumId(clbum.getClbumId());
            student.setMobile(userMobily);
            student.setBirthday(date);
            studentDao.updateById(student);
            out.write(student.getName());
            break;
        default:
            break;
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 查询所有的用户
     */
    private void getTraverseList(StudentDao studentDao, HttpServletRequest request, HttpServletResponse response) {
        studentList = studentDao.allQuery();
        try {
            if (studentList == null || studentList.isEmpty()) {
                request.getRequestDispatcher("/view/grade/fail.jsp").forward(request, response);
            }
            request.setAttribute("studentList", studentList);
            response.sendRedirect("/FirstDemo/StudentServlet?type=1");
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 日期处理
     */
    private Date setDateFormat(String birthday) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   // 获取出生日期
        try {
            Date date = format.parse(birthday);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
