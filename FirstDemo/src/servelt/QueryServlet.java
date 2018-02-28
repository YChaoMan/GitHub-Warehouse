package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDao = null;
    private ClbumDao clbumDao = null;
    private Clbum clbum = null;
    private Student student = null;
    private List<Clbum> clbumList = null;
    private List<Student> studentList = null;
    private Map<String, Object> maps = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    String conditionType = request.getParameter("conditionType"); // 查询类型的获取
	    String query = request.getParameter("query"); // 查询内容
	    if (query == null && "".equals(query)) {    // 对输入内容进行判断
	        System.out.println("查询条件为空~`");
	        return;
	    }
	    System.out.println("queryServlet>> conditionType>> " + conditionType);
	    System.out.println("queryServlet>> query>> " + query);
	    String map = null;
	    clbumDao = new ClbumDao();
        studentDao = new StudentDao();
        clbumDao = new ClbumDao();
	    switch (conditionType) {
        case "1":   // 班级编号
            Integer queryValue = Integer.valueOf(query);
            studentList = studentDao.queryByClbumId(queryValue);    // 根据输入的班级编号进行查询
            clbum = clbumDao.queryById(queryValue); // 查询班级表的所有信息。用以配对
            clbumList = new ArrayList<>();
            clbumList.add(clbum);
            break;
        case "2":   // 班级名称
            clbum = clbumDao.queryByName(query);    // 根据班级名称查询
            studentList = studentDao.queryByClbumId(clbum.getClbumId());
            if (studentList == null && studentList.isEmpty()) {
                return;
            }
            clbumList = new ArrayList<>();
            clbumList.add(clbum);
            break;
        case "3":   // 学生编号
            clbumDao = new ClbumDao();
            studentDao = new StudentDao();
            clbumDao = new ClbumDao();
            Integer queryId = Integer.valueOf(query);
            student = studentDao.queryById(queryId);    // id是唯一的，所以返回一个学生对象
            studentList = new ArrayList<>();
            studentList.add(student);
            clbumList = clbumDao.allQuery();
            break;
        case "4":   // 学生名称
            studentList = clbumDao.allQueryByLike(query);
            clbumList = clbumDao.allQuery();
            break;
        case "5":   // 性别
            clbumList = clbumDao.allQuery();
            studentList = clbumDao.allQueryByLike(query);
            break;
        default:
            break;
        }
	    maps = new HashMap<>();
        maps.put("students", studentList);
        maps.put("clbums", clbumList);
        map = JSON.toJSONString(maps);
        out.write(map);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
