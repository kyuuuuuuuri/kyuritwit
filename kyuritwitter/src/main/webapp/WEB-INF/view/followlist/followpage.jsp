<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title" value="followpage" />
<tiles:put name="content" type="string">

<font size="4">${mydata.username }は${mydata.follow }人をフォローしています</font><br>
<br>
<br>
<c:if test="${empty followList}">
<table id="table-main" border align="left">
<tr><td>まだだれもフォローしていません</td></tr>
</table>
</c:if>

<c:if test="${!empty followList}">
<table id="table-main" border align="left">
<c:forEach var="followList" items="${followList}">

<tr>
<td>
<s:link href="/main/showdata/${followList.usernick}" style="text-decoration: none"> ${followList.usernick} </s:link>
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

</tiles:put>
</tiles:insert>
