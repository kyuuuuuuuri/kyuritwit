<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>

<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />


<style type="text/css">
input.style{
width:50%;
height:6em;
}
</style>

<!--<tiles:insert page="/WEB-INF/view/common/header.jsp"  />-->
<title>メインページ</title>
</head>
<body>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="followedpage" />
<tiles:put name="content" type="string">
<br>
<!-- 吹き出し -->
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">


<!-- mainページのときだけ、つぶやきformを出力する -->
<c:if test="${fFlag==0}">
<div class="scroll"></div>
<s:form>

	いまなにしてる？<html:errors property="tubuyaki" /><br>
	<input type="text" name="tubuyaki"  class="style"  value="" />

<input type="submit" name = "ins_tubuyaki" value="投稿する" class="input_button" />
</s:form>
<font size="2" color=#999999>最新のつぶやき：
${mydata.newMur}　${mydata.newMurD}</font><br>
</c:if>
<br>
<br>
<br>
<div id = "massage"></div>

<!-- つぶやきが一件もなかった場合 -->
<c:if test="${empty murmurList }">
<table id="table-main" border align="left">
<tr>
<td>
表示すべきつぶやきが一件もありません
</td>
</tr>
</table>
</c:if>
<!-- つぶやきが一件もなかった場合END -->


<!-- つぶやきが一件以上ある場合 -->
<c:if test="${!empty murmurList}">
<!-- Ajax用div -->
<span id="message"></span>
<table id="table-main" border align="left">
<c:forEach var="tubuyaki" items="${murmurList}" varStatus="status">

<tr>
<td>


<div id="twitmain" >
<!--<c:out value="${status.index }"></c:out>-->
<font size="4">
<s:link href="#"  onclick="OpenWin('${status.index}'); return false">
<span id ="${status.index}" style="${tubuyaki.tuser.userid}">
${tubuyaki.tuser.usernick}</span></s:link>
</font>

<font size="2" color=#999999>${tubuyaki.tuser.username}</font><br>

<p id="twitid">${f:br(tubuyaki.murmur)}</p><br>
<font color=#808080 size="2">
<fmt:formatDate value="${tubuyaki.dateTime}"pattern="yyyy年MM月dd日 HH時mm分ss秒"/></font>

<!-- 自分のつぶやきじゃない場合リツイートと返信をつける -->
<c:if test="${fFlag==0 && tubuyaki.tuser.userid!=mine}">
<s:link href="/main/retwit/${tubuyaki.murmurid }">リツイート</s:link>
<s:form style="margin: 0px; float: left;">
<input type="button"   onclick="replyan('${status.index}');" value="返信"/>
</s:form>
</c:if>

<!-- 自分のつぶやきだった場合削除リンクをつける -->
<c:if test="${tubuyaki.tuser.userid==mine}">
<s:link href="/main/delete/${tubuyaki.murmurid}">削除</s:link>

</c:if>
</div>


</td>
</tr>
</c:forEach>
</table>

</c:if>

</div>
</div>
</tiles:put>
</tiles:insert>
</body>
</html>