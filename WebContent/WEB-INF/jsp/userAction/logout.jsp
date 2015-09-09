<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>您已退出系统</title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
<link href="${pageContext.request.contextPath}/style/blue/logout.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table border=0 cellspacing=0 cellpadding=0 width=100% height=100%>
		<tr>
			<td align=center>
				<div id=Logout>
					<div id=AwokeMsg>
						<img id=LogoutImg src="${pageContext.request.contextPath}/style/blue/images/logout/logout.gif" border=0 />
						<img id=LogoutTitle src="${pageContext.request.contextPath}/style/blue/images/logout/logout1.gif" border=0 />
					</div>
					<div id=LogoutOperate>
                    <img src="${pageContext.request.contextPath}/style/blue/images/logout/logout2.gif" border=0 />
                    	<!--此处不能写s:a标签，修改了struts2中a标签的源码，需要有session对象，不然会报空指针异常 -->
                     	<a href="${pageContext.request.contextPath}/user_loginUI.action">重新进入系统</a> 
                    <img src="${pageContext.request.contextPath}/style/blue/images/logout/logout3.gif" border=0 /> 
                    	<a href="javascript: window.open('','_self');window.close();">关闭当前窗口</a>
                    </div>
				</div>
			</td>
		</tr>
	</table>
	
</body>
</html>