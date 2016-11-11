<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
	<form action="RegServlet" method="post" onsubmit="return reg(this);">
		<table>
			<tr>
				<td align="right" width="30%" >用户名</td>
				<td><input type="text" name="username" class="box"></td>
			</tr>
			<tr>
				<td align="right">密码:</td>
				<td><input type="password" name="password" class="box"></td>
			</tr>
			<tr>
				<td align="right" >确认密码：</td>
				<td><input type="password" name="password" class="box"></td>
			</tr>
			<tr>
				<td align="right" >性别：</td>
				<td>
					<input type="radio" name="gender" value="男" checked="checked">男
					<input type="radio" name="gender" value="女" >女
				</td>
			</tr>
			<tr>
				<td align="right" >密码找回问题：</td>
				<td><input type="text" name="question" class="box"></td>
			</tr>
			<tr>
				<td align="right" >密码找回答案：</td>
				<td><input type="text" name="answer" class="box"></td>
			</tr>
			<tr>
				<td align="right" >邮箱：</td>
				<td><input type="text" name="email" class="box"></td>
			</tr>
			<tr>
				<td colspan="2" align="center" height="40">
					<input type="submit" value="注册">
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>	
</body>
</html>