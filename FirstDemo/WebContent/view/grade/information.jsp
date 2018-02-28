<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生详细信息</title>
<style type="text/css">
@IMPORT url("/FirstDemo/static/css/information.css");
</style>
</head>
<body>
    <h1>用户详细信息</h1>
	<hr />
	<input type="hidden" name="id" value=<%=request.getParameter("id")%>>
	<table class="table">
		<thead>
			<tr>
				<td>编号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>出生日期</td>
				<td>手机号</td>
				<td>班级编号</td>
			</tr>
		</thead>
		<tbody id="tbody">
		</tbody>
	</table>
	<a href="/FirstDemo/view/grade/student_list.jsp">返回</a>
	
	<script type="text/javascript" src="/FirstDemo/static/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
	$(function() {
		stuMessage();
	})
	
	  function stuMessage() {
		  var data = {
			  id : $("input[name='id']").val()
		  };
		  
		  var strHtml = "";
		  $.ajax({
              data: data,
              url: "/FirstDemo/StudentServlet?type=3",
              type: "post",
              dataType: "json",
              success: function (result) {
            	  strHtml += "<tr><td>" + result.student.id + "</td>";
            	  strHtml += "<td>" + result.student.name + "</td>";
            	  strHtml += "<td>" + (result.student.sex == 1 ? "男" : "女") + "</td>";
            	  strHtml += "<td>" + new Date(result.student.birthday).Format("yyyy-MM-dd") + "</td>";
            	  strHtml += "<td>" + result.student.mobile + "</td>";
            	  strHtml += "<td>" + result.student.clbumId + "</td><tr>";
            	  $("#tbody").append(strHtml);
              },
              error: function (result) {
              }
          }) 
	  }
	
	// 时间格式处理
	Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份 
            "d+": this.getDate(), //日 
            "h+": this.getHours(), //小时 
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
	</script>
</body>
</html>