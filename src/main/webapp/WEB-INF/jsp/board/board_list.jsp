<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>

<script>
	$(document).ready(function(){
		//페이지 번호 이동
		$('#pagingDiv a').click(function(e){
			e.preventDefault();
			$('#pageNum').val($(this).attr("href"));
			pagingForm.submit();
			
		});
		
		$("#write").click(function() {
			location.href = "write";
		});
	});
</script>
<head>
<meta charset="EUC-KR">
<title>WRITE</title>
</head>
<body>
	<table border="1" >
		<tr>
			<th bgcolor="" width="50">NO</th>
			<th bgcolor="" width="200">제목</th>
			<th bgcolor="" width="150">작성자</th>
			<th bgcolor="" width="150">작성일</th>
			<th bgcolor="" width="100">조회수</th>
		</tr>
		<c:choose>
			<c:when test="${!empty boardList}">
				<c:forEach items="${boardList}" var="board">
					<tr>
						<td>${board.idx}</td>
						<td align="left"><a href="${board.idx}">
								${board.title}</a></td>
						<td>${board.writer}</td>
						<td><fmt:formatDate value="${board.reg_date}" pattern="yyyy-MM-dd"/></td>
						<td>${board.cnt}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">등록된 글이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<br>
	<div id="pagingDiv">
			<c:if test="${paging.prev}">
				<a href="${paging.startPage - 1}">이전</a>
			</c:if>
			<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
				&nbsp;<a href="${num}">${num}</a>&nbsp;
			</c:forEach>
			<c:if test="${paging.next}">
				<a id="next" href="${paging.endPage + 1}">다음</a>
			</c:if>
	</div>
	
	<form id="pagingFrm" name="pagingForm" action="list" method="get">
		<input type="hidden" id="pageNum" name="pageNum" value="${paging.cri.pageNum}">
		<input type="hidden" id="amount" name="amount" value="${paging.cri.amount}">
	</form>
	<button id="write" type="button">글쓰기</button>

</body>
</html>