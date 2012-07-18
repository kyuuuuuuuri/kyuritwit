<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="followedpage" />
<tiles:put name="content" type="string">

<div style="margin:10px 0px 25px 10px">
<font size="4">${mydata.username }は${mydata.followed }人にフォローされています</font>
</div>

<c:if test="${empty followedList}">
<table id="table-01" border align="left">
<tr><td>まだだれにもフォローされていません</td></tr>
</table>
</c:if>

<c:if test="${!empty followedList}">
<table id="table-main" border align="left">

<c:forEach var="followed" items="${followedList}">

<tr><td>
<s:link href="/main/showdata/${followed.usernick}" style="text-decoration: none"> ${followed.usernick} </s:link>
<br>
${ followed.newMur }
<font color=#808080 size="2"><fmt:formatDate value="${followed.newMurD}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></font>
<br>
<!-- -->
<c:set var="check" value="0" />
<c:if test="${ mine == mydata.userid }">
<c:forEach begin="0" end="${mydata.follow}" var="i">
<c:if test="${ fc_userid[i] == followed.userid }" >
<c:set var="check" value="1" />
</c:if>

</c:forEach>
<c:if test="${!( check == 1 )}">

<s:link onclick="return confirm('follow OK?');" href="follownow/${followed.userid}">followする</s:link>
</c:if>
<c:set var="check" value="0" />
</c:if>
</td>
</tr>

</c:forEach>

</table>
</c:if>

</tiles:put>
</tiles:insert>
