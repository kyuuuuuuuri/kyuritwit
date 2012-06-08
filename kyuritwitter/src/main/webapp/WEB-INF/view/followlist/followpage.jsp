<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>followページ</title>
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

</head>

<body>
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">
<font size="4">${mydata.username }は${mydata.follow }人をフォローしています</font><br>
<br>
<br>
<c:if test="${empty followList}">
<table id="table-main" border align="left">
<tr>
<td>
まだだれもフォローしていません
</td>
</tr>
</table>
</c:if>

<c:if test="${!empty followList}">
<table id="table-main" border align="left">
<c:forEach var="followList" items="${followList}">

<tr>
<td>
<s:link href="/main/showdata/${followList.userid}" style="text-decoration: none"> ${followList.usernick} </s:link>
<br>
${followList.newMur}
<font color=#808080 size="2"><fmt:formatDate value="${followList.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></font>
<br>

<c:if test="${mydata.userid==mine}">
<s:link onclick="return confirm('delete OK?');" href="unfollow/${followList.userid}">followを解除</s:link>
</c:if>

</td>
</tr>


</c:forEach>

</table>
</c:if>
<table border="0">
<tr>
<td colspan="3">${mydata.usernick }</td>
</tr>
<tr>
<td>${mydata.follow }<br><s:link href="followpage/${mydata.userid }" style="text-decoration: none">フォロー<br>している</s:link></td>
<td>${mydata.followed }<br><s:link href="followedlist/${mydata.userid }"style="text-decoration: none">フォロー<br>されている</s:link></td>
</tr>
<tr>
<td>${mydata.postNum }　　<s:link href="/main/showdata/${mydata.userid}"style="text-decoration: none">投稿数</s:link></td>
</tr>
</table>

<br clear="left">

<c:if test="${hasPrev}">
	<a href="?page=${page-1 }">&lt;次へ</a>
</c:if>
<c:if test="${hasNext}">
	<a href="?page=${page+1 }">前へ&gt;</a>
</c:if>
</div>
</div>
</body>
</html>
