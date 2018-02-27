<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This is a JSP</title>
</head>
<body>
<script type="text/javascript" src="/hello/static/js/jquery-3.2.1.min.js"></script>
    <h2>Hello This is JSP</h2>
    <hr />
    input:>><input type="text" id="name" name="name"/>&nbsp;<input type="text" id="input">
    <button onclick="getExampleName()">测 试</button>
    <script type="text/javascript">
    function getExampleName() {
//     	alert($("input[type='text']").val());  // 根据属性获取值
//     	alert($("#name").val());   // 根据id进行获取值
        var nameData = {    // 需要传输的数据
//         	name:$("#name").val()
        	name:$("input[name='name']").val()
		};
        $.ajax({    // 传输url、数据、提交方式、提交成功之后、提交失败之后
        	url:"/hello/HelloServlet",
        	data: nameData,
        	type: "post",
        	success: function (result) {
        		if (result == "200") {
        			console.log(result);
        			$("#input").val(result);
        		}
        		    alert("传递数据" + nameData.name + "成功~`");
        	},
        	error: function (err) {
        		
        	}
        });
    }
    </script>
</body>
</html>