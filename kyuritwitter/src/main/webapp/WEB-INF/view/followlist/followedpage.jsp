<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>フォロワ―ページ</title>
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />

<tiles:insert page="/WEB-INF/view/common/header.jsp"  />


</head>
<body>
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">

<font size="4">${mydata.username }は${mydata.followed }人にフォローされています。</font>

<br><br><br>

<c:if test="${empty followedList}">
<table id="table-01" border align="left">
<tr>
<td>
まだだれにもフォローされていません
</td>
</tr>

</table>
</c:if>

<c:if test="${!empty followedList}">
<table id="table-main" border align="left">

<c:forEach var="followed" items="${followedList}">

<tr>
<td>
<s:link href="/main/showdata/${followed.userid}" style="text-decoration: none"> ${followed.usernick} </s:link>
<br>
${followed.newMur}
<font color=#808080 size="2"><fmt:formatDate value="${followed.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></font>
<br>
<!-- ユーザがすでにフォローしているのかを検証する -->
<c:set var="check" value="0" />
<c:if test="${ mine==mydata.userid}">
<c:forEach begin="0" end="${mydata.follow}" var="i">
<c:if test="${fc_userid[i]==followed.userid}" >
<c:set var="check" value="1" />
</c:if>

</c:forEach>
<c:if test="${!(check == 1)}">

<s:link onclick="return confirm('follow OK?');" href="follownow/${followed.userid}">followする</s:link>
</c:if>
<c:set var="check" value="0" />
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
<td>${mydata.followed }<br><s:link href="followedlist/${mydata.userid }" style="text-decoration: none">フォロー<br>されている</s:link></td>
</tr>
<tr>
<td>${mydata.postNum }　　<s:link href="/main/showdata/${mydata.userid}" style="text-decoration: none">投稿数</s:link></td>
</tr>
</table>

<br clear="left">

<c:if test="${hasPrev}">
	<a href="?page=${page-1 }">&lt;前へ</a>
</c:if>
<c:if test="${hasNext}">
	<a href="?page=${page+1 }">次へ&gt;</a>
</c:if>
</div>
</div>
</body>
</html>
