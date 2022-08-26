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
	<title>게시판 목록</title>
</head>
<body>
<article>
	<div class="container">
		<a href="/board/write"><button type="button" class="btn btn-sm btn-primary" >등록</button></a>
		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<colgroup>
					<col style="width:5%;" />
					<col style="width:auto;" />
					<col style="width:10%;" />
					<col style="width:10%;" />
				</colgroup>
				<thead>
					<tr>
						<th>no</th>
						<th>글제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty list }" >
							<tr><td colspan="4" align="center">데이터가 없습니다.</td></tr>
						</c:when> 
						<c:when test="${!empty list}">
							<c:forEach var="list" items="${list}">
									<tr>
										<td><c:out value="${list.tbSeq}"/></td>
										<td><a href="/board/detail?tbSeq=<c:out value="${list.tbSeq}"/>"><c:out value="${list.tbTitle}"/></a></td>
										<td><c:out value="${list.tbRegId}"/></td>
										<td><c:out value="${list.tbRegDt}"/></td>
									</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</article>
</body>
</html>

