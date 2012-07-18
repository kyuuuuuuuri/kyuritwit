<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />

<tiles:insert page="/WEB-INF/view/common/header.jsp"  />


<title>login</title>
</head>
<body>
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">
<h2>ログイン</h2>
<br>
<s:form>
    <html:errors/>
	<label class="w1" for="userid">名前:</label>
	<input type="text" name="UserName" class="" id="userid"/>
	<br>
	<label class="w1" for="pass">パスワード:</label>
	<input type="password" name="Pass" autocomplete="off" id="pass"/>
	<!--<html:text property="Pass" /></div><br>-->
<br>
	<s:submit property = "loginSubmit" >ログインする</s:submit>
</s:form>

<s:form>
<b>ユーザ登録(無料)</b>
<s:submit property="userentry">ユーザ登録</s:submit>

</s:form>
</div>
</div>

</body>
</html>