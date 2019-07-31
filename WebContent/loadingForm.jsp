<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	background-color: pink;
}
</style>
</head>
<body>
	<h2>로그인</h2>
	<form action="loginPro.jsp">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" required="required"></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="passwd" required="required"></td>
			</tr>
			<tr>
				<td><input type="submit" value="확인"></td>
				<td><input type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
	<p>
		<input type="button" value="회원가입"
			onclick="location.href='joinForm.jsp'">
</body>
</html>