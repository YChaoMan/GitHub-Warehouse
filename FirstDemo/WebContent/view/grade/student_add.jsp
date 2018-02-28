<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户新增</title>
<style type="text/css">
@IMPORT url("/FirstDemo/static/css/student_add.css");
</style>
</head>
<body>
    <script type="text/javascript" src="/FirstDemo/static/js/jquery-3.2.1.min.js"></script>
	<h1>用户新增</h1>
	<hr />
	<form id="form">
		<input type="hidden" value="2" name="type" />
		 姓名：<input type="text"name="username" /><br />
		  性别:<input type="radio" name="sex" value="1" id="boy" checked="checked" /><label for="boy">男</label>
		  <input type="radio" name="sex" value="0" id="girl" /><label for="girl">女</label><br />
		出生日期:<input type="date" name="birthday" /><br />
		手  机  号:<input type="text" name="mobile" /><br /> 
		班级编号:
		<select name="clbumId">
		  <c:forEach var="clbumList" items="${clbumList }">
		      <option value="${clbumList.clbumId }">${clbumList.clbumName }</option>
		  </c:forEach>
		</select><br />
		<div class="button">
		  <input type="button" onclick="insert()" value="提交" />&nbsp;&nbsp;
		  <input type="reset" value="重置" />&nbsp;&nbsp;
		  <input type="button" onclick="window.location.href='/FirstDemo/view/grade/student_list.jsp'" value="返回">
		</div>
	</form>
	<script type="text/javascript" src="/FirstDemo/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	  function insert() {
		  var data = $("#form").serialize();
		  $.ajax({
			  url: "/FirstDemo/StudentServlet",
			  data: data,
			  type: "post",
			  success: function (result) {
				  alert("新增用户> > " + result + " < <成功~`");
				  window.location.href = "/FirstDemo/view/grade/student_list.jsp";
			  },
			  error: function () {}
		  })
	  }
	</script>
</body>
</html>