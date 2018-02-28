<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级管理</title>
</head>
<body>
    <h1>修改班级信息</h1>
    <hr >
    <form action="/FirstDemo/ClbumServlet" method="post">
      <input type="hidden" name="type" value="3">
                    班级编号：<input type="text" name="clbumId" value="${clbum.clbumId}" readonly="readonly"><br />
                    班级名称：<input type="text" name="clbumName" value="${clbum.clbumName}"><br />
      <input type="submit" value="确定" />&nbsp;&nbsp;&nbsp;<input type="reset" value="重置" />
    </form>
</body>
</html>