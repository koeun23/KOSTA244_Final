<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" 
	crossorigin="anonymous">
	<style>
	body {
		padding-top: 70px;
		padding-bottom: 30px;
	}
	.board_title {
		font-weight : 700;
		font-size : 22pt;
		margin : 10pt;
	}
	.board_info_box {
		color : #6B6B6B;
		margin : 10pt;
	}
	.board_author {
		font-size : 10pt;
		margin-right : 10pt;
	}
	.board_date {
		font-size : 10pt;
	}
	.board_content {
		color : #444343;
		font-size : 12pt;
		margin : 10pt;
	}
	</style>
	<title>게시판 상세조회</title>
</head>
<script type="text/javascript">
	function goList(){
		location.href="/board/list";	
	}
</script>
<body>
	<article>
		<div class="container" role="main">
			<h2>게시판 등록</h2>
			<c:choose>
				<c:when test="${not empty boardVo}">
			    	<c:set var="formAction" value="/board/update" />
			  	</c:when>   
			  	<c:otherwise>
			    	<c:set var="formAction" value="/board/insert" />
			  	</c:otherwise>
			</c:choose>
			<form method="post" action="${formAction}" >
				<input type="hidden" name="tbSeq" value="${boardVo.tbSeq}"/>
				<div class="bg-white rounded shadow-sm">
					<div class="board_info_box">
						제목<input class="board_title" type="text" id="tbTitle" name="tbTitle" value="${boardVo.tbTitle}">
						<br/>
						내용<input class="board_title" type="text" id="tbContent" name="tbContent" value="${boardVo.tbContent}">
					</div>
				</div>
				<div style="margin-top : 20px">
					<button type="submit" class="btn btn-sm btn-primary" >저장</button>
					<button type="button" class="btn btn-sm btn-primary" onclick="javascript:goList();">목록</button>
				</div>
			</form>
		</div>
	</article>
</body>
</html>

