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
 

 
 <h1>게시물 목록</h1>
 
<c:forEach items="${ articles }" var="a">
	<div>
 		번호 : ${ a.no }
 		<a href="/article?action=detail&idx=${ a.no }"> 제목 : ${ a.title } </a>
 		작성자 : ${ a.writer }
 		작성일 : ${ a.regDate }
 	</div>
</c:forEach>
 
<a href="/article?action=add">글쓰기</a>
 
</body>
</html>