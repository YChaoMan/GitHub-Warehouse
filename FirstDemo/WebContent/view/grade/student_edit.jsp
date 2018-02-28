<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级修改</title>
<style type="text/css">
@IMPORT url("/FirstDemo/static/css/student_edit.css");
</style>
</head>
<body>
<script type="text/javascript" src="/FirstDemo/static/js/jquery-3.2.1.min.js"></script>
	<h1>班级修改</h1>
    <hr/>
	<input type="hidden" id="type" value=<%=request.getParameter("id") %> name="type"/>
	<form id="form" >
	</form>
	<div class="footnav">
      <input type="button" onclick="submitData()" value="提交"/>
      <input type="reset" />
      <input type="button" onclick="window.location.href='/FirstDemo/view/grade/student_list.jsp'" value="返回"/>
    </div>
	<script type="text/javascript">
	$(function () {
		edit();
	})
	
	// 打印页面
	function edit() {
		var data = {
			id : $("input[name='type']").val()
		};
		
		var strHtml = "";
		$.ajax({
			url : "/FirstDemo/StudentServlet?type=3",
			data : data,
			type : "post",
			dataType : "json",
			success: function (data) {
				strHtml += "<input type=\"hidden\" value=\"" + data.student.id + "\" name=\"id\">";
				strHtml += "编号：<input type=\"text\" name=\"userId\" value=\""+ data.student.id + "\" readonly/><br />";
				strHtml += "姓名：<input type=\"text\" name=\"userName\" value=\"" + data.student.name + "\" /><br />";
				strHtml += "性别：<input type=\"radio\" id=\"boy\" name=\"sex\" value=\"1\" checked=\"checked\" ><label for=\"boy\">男</label>";
				strHtml += "<input type=\"radio\" id=\"girl\" name=\"sex\" value=\"0\" ><label for=\"girl\">女</label><br />";
				strHtml += "出生日期：<input type=\"date\" name=\"birthday\" value=\""+ new Date(data.student.birthday).Format("yyyy-MM-dd") + "\"/><br />";
				strHtml += "手  机  号：<input type=\"text\" name=\"userMobily\" value=\"" + data.student.mobile + "\" /><br />";
				strHtml += "班级名称：<input type=\"text\" name=\"clbumName\" value=\"" + data.clbum.clbumName + "\" /><br />";
				$("#form").append(strHtml);
	        },
	        error: function (err) {
	        }
		});
		
// 		时间处理
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
	}
	</script>
	
<!-- 提交数据 -->
	<script type="text/javascript">
        function submitData() {
            var formData = $("#form").serialize();
            $.ajax({
            	data: formData,
                url: "/FirstDemo/StudentServlet?type=5",
                type: "post",
                success: function(result) {
                	alert("修改用户<< " + result + " >>成功!~");
                	window.location.href = "/FirstDemo/view/grade/student_list.jsp";
                },
                error: function(result) {
                	alert("修改用户<< " + result + " >>失败!~");
                	window.location.href = "/FirstDemo/view/grade/student_list.jsp";
                }
                		
            })
        }
    </script>
</body>
</html>