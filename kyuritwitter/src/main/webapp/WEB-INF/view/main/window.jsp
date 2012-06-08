<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>

<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

<title>new window</title>
<style type="text/css">
body {
background:#C0DEED;
}

</style>
</head>
<body>
	<div id="balloon-c">

<h2>user_data</h2>
<div>
<h1>${user_window.usernick }</h1>
<table>
<tr>
<td>フォロー人数<br>${user_window.follow}</td>
<td>フォローされている<br>${user_window.followed}</td>
<td>投稿数<br>${user_window.postNum}</td>
</tr>
</table>
</div>
つぶやき：
<c:forEach var="tubuyaki" items="${newWindoTwit}" >
<div>
${tubuyaki.murmur}
</div>
</c:forEach>

</div>


</body>
</html>
