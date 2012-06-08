<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>サーチリザルトのページ</title>
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<tiles:insert page="/WEB-INF/view/common/header.jsp"  />

</head>
<body>
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">
<br>

${search}の検索結果<br>

<c:if test="${empty searchUser }">
<font color="ff0000">対象のユーザはみつかりません。</font>
</c:if>



<c:if test="${!empty searchUser}">
<table id="table-main">

<c:forEach var="search" items="${searchUser}">

<tr>
<td>
<s:link href="/main/showdata/${search.userid}"> ${search.usernick} </s:link>
<br>

<c:if test="${empty search.newMur}">
　
</c:if>
<c:if test="${!empty search.newMur }">
${search.newMur }
<font color=#808080 size="2"><fmt:formatDate value="${search.newMurD}"pattern="yyyy年MM月dd日 HH時mm分ss秒"/></font>
</c:if>
<!-- ユーザがすでにフォローしているのかを検証する -->
<c:forEach begin="0" end="${followcheckcount}" var="i">
<c:if test="${fc_userid[i]==search.userid}" >
<c:set var="check" value="1" />
</c:if>
</c:forEach>
<c:if test="${mine==search.userid }"><c:set var="check" value="1" />
</c:if>
<c:if test="${!(check == 1)}">
<s:link onclick="return confirm('follow OK?');" href="infollow/${search.userid}">followする</s:link>
</c:if>
<c:set var="check" value="0" />

</td>
</tr>


</c:forEach>

</table>

<c:if test="${hasPrev}">
	<a href="?page=${page-1 }">&lt;前へ</a>
</c:if>
<c:if test="${hasNext}">
	<a href="?page=${page+1 }">次へ&gt;</a>
</c:if>

</c:if>
</div>
</div>
</body>
</html>

