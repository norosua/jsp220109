<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="board.model.SqlMapper" %>
<%@ page import="board.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
 <%
	SqlMapper mapper = new SqlMapper();
 	ArrayList<Article> articles = mapper.getArticleList();
 %>
 
 <h1>게시물 목록</h1>
 
 <% for(int i = 0; i < articles.size(); i++) { %>
 	<div>
 		번호 : <%= articles.get(i).getNo() %>
 		제목 : <%= articles.get(i).getTitle() %>
 		작성자 : <%= articles.get(i).getWriter() %>
 		작성일 : <%= articles.get(i).getRegDate() %>
 	</div>
 	<hr>
 <% } %>
 
</body>
</html>