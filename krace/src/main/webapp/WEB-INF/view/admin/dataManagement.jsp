<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>KRace</title>
<script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
<script type="text/javascript">

function updateHorseOwner() {
	
	$("#statusDiv").html("업데이트 중입니다. 잠시만 기다리세요...");
	
	$.ajax({
		url: 'updateHorseOwner.do',
		type: 'POST',
		data: {
			meet: "1"
		},
		success:function(data) {
			var resultCode = $(data).find("resultCode").text();
			if (resultCode == "0") {
				alert("성공");
				$("#statusDiv").html("");
			}
			else {
				alertErrorMsg(resultCode);
				$("#statusDiv").html("");
			}
		}
	});
}

</script>

</head>

<body>

<!-- 메뉴 -->
<div id="menuDiv">
	<ul>
		<li><a href="">게시판</a>
			<ul>
				<li><a href="">자유게시판</a></li>
			</ul>
		</li>
		<li><a href="">전문가</a>
			<ul>
				<li><a href="">전문가게시판</a></li>
			</ul>
		</li>
		<li><a href="">SMS문자</a>
			<ul>
				<li><a href="">SMS</a></li>
			</ul>
		</li>
		<li><a href="">서울경마정보</a>
			<ul>
				<li><a href="">출전표</a></li>
				<li><a href="">경주성적</a></li>
				<li><a href="">경주마정보</a></li>
				<li><a href="">기수정보</a></li>
				<li><a href="">조교사정보</a></li>
				<li><a href="">마주정보</a></li>
			</ul>
		</li>
		<li><a href="">운영자메뉴</a>
			<ul>
				<li><a href="">회원관리</a></li>
				<li><a href="">전문가관리</a></li>
				<li><a href="">경마정보관리</a></li>
			</ul>
		</li>
		
	</ul>
</div>


<!-- 로그인 영역-->
<div id="loginDiv"> 

</div>

<div id="contentsDiv">

<a href="javascript:updateHorseOwner();">마주정보 업데이트</a>



<div id="statusDiv"></div>


</div>



</body>
</html>
