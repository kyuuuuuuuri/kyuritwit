<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ユーザ登録</title>
<tiles:insert page="/WEB-INF/view/common/header.jsp"  />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

</head>
<body>
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">
<h1>ツイッターに参加しました</h1>
<br>
<s:form>
${tuser.userNick}
さんはついったーに参加されました。

ログインをクリックしてログインしつぶやいてください。

	<input type="submit" name = "loginNow" value="ログインする" />
</s:form>
</div>
</div>
</body>
</html>


