<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시물 상세</h1>
<div>
	<hr>
	<div>
		제목 : ${ article.title }
	</div>
	<hr>
	<div>
		내용 : ${ article.body }
	</div>
	<hr>
	<div>
		작성자 : ${ article.writer }
	</div>
	<hr>
		<a href="/article?action=update&idx=${ article.no }">수정</a> <a href="/article?action=doDelete&idx=${ article.no }">삭제</a> <a href="/article?action=list">뒤로가기</a>
</div>
</body>
</html>