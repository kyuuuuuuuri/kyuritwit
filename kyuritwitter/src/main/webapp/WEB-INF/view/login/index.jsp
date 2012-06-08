<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>

<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

<title>loginページ</title>
<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

</head>
<body>
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">

<!-- <span id="message"></span><br />
<input type="button" value="hello"
    onclick="$('#message').load('hello',{'greeting':'Hello'});"/>
    -->

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
