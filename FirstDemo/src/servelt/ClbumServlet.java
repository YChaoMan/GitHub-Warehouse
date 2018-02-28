package servelt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClbumDao;
import entity.Clbum;

/**
 * Servlet implementation class ClbumServlet
 */
@WebServlet("/ClbumServlet")
public class ClbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClbumDao clbumDao = null;
	private Clbum clbum = null;
	private List<Clbum> clbumList = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClbumServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    clbumDao = new ClbumDao();
	    clbumList = clbumDao.allQuery();   //查询所有班级信息
	    String type = request.getParameter("type");
	    String clbumName = request.getParameter("clbumName");
	    String clbumId = request.getParameter("clbumId");
	    switch (type) {
	    case "1":  // 增加班级信息
	        if (clbumName != null && !clbumName.isEmpty()) {
	            System.out.println("ClbumServlet>> clbumName>> " + clbumName);
	            clbumDao.addClbum(clbumName);
	        }
	        response.sendRedirect(request.getContextPath() + "/ClbumServlet?type=4");
	        break;
	    case "2":  // 删除班级信息
	        System.out.println(clbumId);
	        if (clbumId != null && !clbumId.isEmpty()) {
	            clbumDao.delById(Integer.valueOf(clbumId));
	        }
	        response.sendRedirect("/FirstDemo/ClbumServlet?type=4");
	        break;
        case "3":   // 修改班级信息
            clbum = new Clbum();
            if (clbumId != null && !clbumId.isEmpty()) {
                clbum.setClbumId(Integer.valueOf(clbumId));
                clbum.setClbumName(clbumName);
                System.out.println("ClbumServlet>> clbumName>> " + clbumName);
                clbumDao.updateById(clbum);
            } else {
                System.out.println("修改格式错误~`");
                return;
            }
            response.sendRedirect("/FirstDemo/ClbumServlet?type=4");
            break;
        case "4":   // 查询班级信息
            if (clbumList != null && clbumList.size() > 0) {
                request.setAttribute("clbumList", clbumList);
                String mark = request.getParameter("mark");
                if ("2".equals(mark)) {
                    request.getRequestDispatcher("/view/grade/student_add.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/view/clbum/clbum.jsp").forward(request, response);
                }
            }
            break;
        case "5":   // 根据班级编号查询班级信息
            System.out.println(clbumId);
            if (clbumId != null && !clbumId.isEmpty()) {
                clbum = clbumDao.queryById(Integer.valueOf(clbumId));
                request.setAttribute("clbum", clbum);
                request.getRequestDispatcher("/view/clbum/clbum_edit.jsp").forward(request, response);
            }
            break;
        default:
            request.getRequestDispatcher("/view/grade/fail.jsp").forward(request, response);
            break;
        }
	    response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
