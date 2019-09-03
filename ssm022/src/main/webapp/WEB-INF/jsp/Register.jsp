<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h2>注册用户</h2>
   <form action="${pageContext.request.contextPath}/user/register" method="POST">
      用户:<input name="username"/> <br/>
      密码:<input name="password" type="password"/> <br/>
      <input type="submit" value="注册"/>
   </form>
</body>
</html>