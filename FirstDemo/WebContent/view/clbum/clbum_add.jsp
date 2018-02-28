<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级新增</title>
</head>
<body>
    <h1>班级新增</h1>
    <hr />
    <form action="/FirstDemo/ClbumServlet" method="post">
      <input type="hidden" name="type" value="1">
      <input type="text" name="clbumName" placeholder="这里输入班级名称" />
      <input type="submit" value="新增" />
    </form>
</body>
</html>