<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />

<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

<title>entry</title>
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
