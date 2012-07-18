<table border="0">
<tr>
<td colspan="3"><h2>${mydata.usernick }</h2></td>
</tr>
<tr>
<td>${mydata.follow }<br><s:link href="/followlist/followpage/${mydata.userid }" style="text-decoration: none">フォロー<br>している</s:link></td>
<td>${mydata.followed }<br><s:link href="/followlist/followedlist/${mydata.userid }"style="text-decoration: none">フォロー<br>されている</s:link></td>
</tr>
<tr>
<td>${mydata.postNum }　　<s:link href="/main/showdata/${mydata.usernick}"style="text-decoration: none">投稿数</s:link></td>
</tr>
</table>