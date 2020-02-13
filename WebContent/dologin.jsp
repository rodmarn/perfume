<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:useBean id="loginUser" class="com.po.Users" scope="page"></jsp:useBean>
<jsp:useBean id="userDAO" class="com.dao.UsersDAO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="loginUser"/>
<%
	if(userDAO.usersLogin(loginUser))
	{
		session.setAttribute("loginUser", loginUser.getUsername());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	else{
		response.sendRedirect("login.jsp");
	}
%>