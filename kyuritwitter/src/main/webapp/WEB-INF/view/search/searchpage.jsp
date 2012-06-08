<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>検索ページ</title>
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

<style type="text/css">
input.style{
width:40%;
height:2em;
}
</style>

</head>
<body>
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">
<h1>友だちをみつけて、フォローしましょう！</h1>
<br>

ついったーに登録済みの友人を検索できます<br>
<br>
だれを検索しますか<br>
<s:form>
	<input type="text" name="search" class="style" />
	<input type="submit" name = "searchSubmit" value="検索" />
</s:form>
ユーザ名や名前で検索
</div>
</div>

</body>
</html>

