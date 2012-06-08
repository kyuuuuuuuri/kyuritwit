<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ヘッダーページ</title>
<style type="text/css">
body {
background:#C0DEED;
}

</style>
</head>
<body>
<font size="6">
<s:link href="/main/main"><img src="${f:url('/img/twitter.jpg')}"></s:link>
</font>　　　　　　　
<c:if test="${ mine == 0 }">

<s:link href="/main/">ホーム</s:link>
　
<s:link href="/user/">ユーザ登録</s:link>
　
<s:link href="/login/">ログイン</s:link>

</c:if>

<c:if test="${ mine != 0 }">

<s:link href="/main/">ホーム</s:link>
　
<s:link href="/search/">友人を検索</s:link>
　
<s:link href="logout">ログアウト</s:link>

</c:if>

</body>
</html>

