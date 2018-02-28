<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table>
      <thead>
        <tr>
          <td>编号</td>
          <td>姓名</td>
          <td>性别</td>
          <td>班级名称</td>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="studentList" items="${studentList }">
          <tr>    
                    <td>${studentList.id }</td>
                    <td>${studentList.name}</td>
                    <!-- 三目运算符进行性别判断 -->
                    <td>
                       ${studentList.sex == 1?'男':'女'}
                    </td>
                    <td>
                       <c:forEach var="clbumList" items="${clbumList}">
                         <c:if test="${clbumList.clbumId eq studentList.clbumId}">
                            ${clbumList.clbumName }
                         </c:if>
                       </c:forEach>
                    </td>
                </tr>
        </c:forEach>
      </tbody>
    </table>
</body>
</html>