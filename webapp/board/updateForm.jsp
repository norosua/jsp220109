<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 게시물 수정</h1>

<form action="/article">
	<div>
	<input type="text" name="title" placeholder="제목을 입력해주세요." value="${ article.title }"/>
	</div>
	<div>
	<input type="text" name="body" placeholder="내용을 입력해주세요." value="${ article.body }"/>
	</div>
	<div>
	<input type="hidden" name="action" value="doUpdate"/>
	<input type="hidden" name="idx" value="${ article.no }"/>
	<input type="submit" value="수정"/>
	</div>
</form>
</body>
</html>