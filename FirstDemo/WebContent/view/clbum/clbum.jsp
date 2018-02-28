<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级管理</title>
<style type="text/css">
@IMPORT url("/FirstDemo/static/css/clbum.css");
</style>
</head>
<body>
    <h1>班级管理</h1>
    <hr />
    <table>
      <thead>
        <tr>
          <td>班级编号</td>
          <td>班级名称</td>
          <td>可行操作</td>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="clbumList" items="${clbumList }">
        <tr>
          <td>${clbumList.clbumId}</td>
          <td>${clbumList.clbumName }</td>
          <td>
            <a href="/FirstDemo/ClbumServlet?type=5&clbumId=${clbumList.clbumId }">修改</a>
            <a href="/FirstDemo/ClbumServlet?type=2&clbumId=${clbumList.clbumId }">删除</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <div class="footnav">
      <a href="/FirstDemo/view/clbum/clbum_add.jsp">新增班级</a>
      <a href="/FirstDemo/view/grade/student_list.jsp">返 回</a>
    </div>
</body>
</html>