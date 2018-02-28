<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/FirstDemo/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<title>班级预览</title>
<style type="text/css">
table{
  border-top:1px solid #000;
}
tr,td{
  border:1px dashed #CCC;
}
</style>
</head>
<body>
    <h1>信息管理</h1>
    <hr />
    <div>
    <div class="panel-heading">
<!--       <form action="/FirstDemo/StudentServlet?mark=1&type=1" method="post"> -->
      <form id="form">
        <label>多  样  查  询 >></label>
        <input type="text" placeholder=" 请  选  择  查  询  的  类  型  " name="query" size="80">
        <select name="conditionType" class="btn btn-info dropdown-toggle">
          <option>全 &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;部</option>
          <option value="1">班   级   编   号</option>
          <option value="2">班   级   名   称</option>
          <option value="3">学   生   编   号</option>
          <option value="4">学   生   姓   名</option>
          <option value="5">性 &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;别</option>
        </select>
        <input type="button" onclick="check(1)" class="btn btn-success" value="查询" />
        <a style="margin:0 10px;" href="/FirstDemo/ClbumServlet?type=4&mark=2" class="btn btn-primary btn-lg active">新增</a>
        <a style="margin-right:10px;" href="/FirstDemo/ClbumServlet?type=4" class="btn btn-lg btn-primary">班级管理</a>
        <button onclick="back()" class="btn btn-default btn-lg">B a c k</button>
      </form>
    </div>
    </div>
    <table style="text-align:center;margin:10px 0;" class="table">
        <caption style="text-align:center;font-size:20px;font-weight:bold;">用户预览</caption>
        <thead>
          <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>班级名称</td>
            <td>执行操作</td>
          </tr>
        </thead>
        <tbody id="tbody">
		</tbody>
    </table>
    <script type="text/javascript" src="/FirstDemo/static/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/FirstDemo/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	$(function () {    // 目的是为了以进入页面就开始运行方法
    		check(0);
    	});
    	
    	// 查询开始
        function check(type) {
    	  var data = $("#form").serialize();
    	  var queryType = $("select[name='conditionType']").val();
    	  if (parseInt(queryType) > 0) {   //如果选择了查询类型
    		  var url = "/FirstDemo/QueryServlet";
    	  } else {
    		  var url = "/FirstDemo/StudentServlet?type=1";
    	  }
    	  console.log(url);
      	  $.ajax({
    		url: url,
    		type: "post",
    		data: data,
    		dataType: "json",
    		success: function (result) {
    			var strHtml = "";    // 拼接html
    			var trStyle = "";    //　设置表格样式
    			var classCount = 0;  // 标志选择的样式
    			console.log(result.students.id);
    			$.each (result.students, function (i, n) {
    				switch (classCount++) {   // 表格样式设置
                      case 0:trStyle = "active";break;
                      case 2:trStyle = "success";break;
                      case 4:trStyle = "info";break;
                      case 6:trStyle = "warning";break;
                      case 8:trStyle = "danger";break;
                      default:trStyle= "";break;
                    }
    				if (classCount == 9) {
    	                 classCount = 1;
    	            }
    				// 开始拼接html代码
    				strHtml += "<tr class=" + trStyle + "><td>" + n.id + "</td>"
                    strHtml += "<td>" + n.name + "</td><td>" + (n.sex == 1? "男": "女") + "</td>"
                    var b = true;
                    $.each (result.clbums, function (ic, nc) {
                       if (nc.clbumId == n.clbumId) {
                           b = false;
                           strHtml += "<td>" + nc.clbumName + "</td>"
                       }
                    });
                    if (b) {
                    	strHtml += "<td></td>"
                    }
                    strHtml += "<td><button onclick=\"window.location.href='/FirstDemo/StudentServlet?type=4&id=" + n.id+ "'\">删除</button> /"
		                + " <button onclick=\"window.location.href='/FirstDemo/view/grade/student_edit.jsp?id=" + n.id+ "'\">修改</button> /"
                        + " <button onclick=\"window.location.href='/FirstDemo/view/grade/information.jsp?id=" + n.id+ "'\">详细信息</button></td></tr>";
    			});
    			if (type != 0) {
    	            $("#tbody").html("");
    	        }
    			$("#tbody").append(strHtml); // 后面追加
    		},
    		error: function () {
    		}
    	 })
      }
    	
        // 返回按钮调用
        function back() {
            if (type != 0) {
                $("#tbody").html("");
            }
            $.ajax ({
                url: "/FirstDemo/StudentServlet?type=1",
                type: "post",
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    var strHtml = "";
                    var trStyle = "";
                    var classCount = 0;
                    $.each (result.students, function (i, n) {
                        switch (classCount) {   // 表格样式设置
                          case 0:trStyle = "active";break;
                          case 2:trStyle = "success";break;
                          case 4:trStyle = "info";break;
                          case 6:trStyle = "warning";break;
                          case 8:trStyle = "danger";break;
                          default:trStyle="";break;
                        }
                        classCount++;
                        if (classCount == 9) {
                             classCount = 1;
                          }
                        // 开始拼接html代码
                        strHtml += "<tr class=" + trStyle + "><td>" + n.id + "</td>"
                        strHtml += "<td>" + n.name + "</td><td>" + (n.sex == 1? "男": "女") + "</td>"
                        var b = true;
                        $.each (result.clbums, function (ic, nc) {
                            if (nc.clbumId == n.clbumId) {
                                b = false;
                                strHtml += "<td>" + nc.clbumName + "</td>"
                            }
                        });
                        if (b) {
                            strHtml += "<td></td>"
                        }
                        strHtml += "<td><button onclick=\"window.location.href='/FirstDemo/StudentServlet?type=4&id=" + n.id+ "'\">删除</button> /"
                            + " <button onclick=\"window.location.href='/FirstDemo/view/grade/student_edit.jsp?id=" + n.id+ "'\">修改</button> /"
                            + " <button onclick=\"window.location.href='/FirstDemo/view/grade/information.jsp?id=" + n.id+ "'\">详细信息</button></td></tr>";
                    });
                    $("#tbody").append(strHtml);
                },
                error: function () {
                }
            })
        }
    </script>
</body>
</html>