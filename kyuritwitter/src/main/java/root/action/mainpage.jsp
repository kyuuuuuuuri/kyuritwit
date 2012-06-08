<%@page pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${f:url('/js/jquery.js')}"></script>

<script type="text/JavaScript"><!--

	$(function(){
		//入力フォームの色設定
		$('input[value=""]').val("140字以内で入力してください")
			.css("color", "#969696");
		$("input").focus(function(){
			$(this).css("background-color", "#dbdbff");
			if(this.value=="140字以内で入力してください"){
				$(this).val("").css("color", "#000");
			}
		});
		$("input").blur(function(){
			$(this).css("background-color", "#fff");
			if(this.value==""){
				$(this).val("140字以内で入力してください")
					.css("color","#969696");
			}
			if(this.value!="140字以内で入力してください")
				$(this).css("color","#000");
		});

		//delete
		$("#deletesubmit").click(function(e){

			var delete_userid= $(this).attr('style');

			$.post('${f:url('delete1')}', {
				'delete_id' : delete_userid
				}, function(){
					alert('成功！！');
					("#twitmain").remove();
				});
		});
		//retwit Ajax
		$("#retwit").click(function(){

		});

	});

// --></script>
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />


<style type="text/css">
input.style{
width:50%;
height:3em;
}
</style>

<tiles:insert page="/WEB-INF/view/common/header.jsp"  />
<title>メインページ</title>
</head>
<body>
<br>
<!-- 吹き出し -->
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">


<!-- mainページのときだけ、つぶやきformを出力する -->
<c:if test="${fFlag==0}">

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


<span id="message1"></span><br />
<input type="button" value="hello"
    onclick="$('#message1').load('hello',{'greeting':'Hello'});"/>
<!-- つぶやきが一件以上ある場合 -->
<c:if test="${!empty murmurList}">
<!-- Ajax用div -->
<span id="message"></span>
<table id="table-main" border align="left">
<c:forEach var="tubuyaki" items="${murmurList}">

<tr>
<td>


<div id="twitmain" >
<font size="4">
<s:link href="showdata/${tubuyaki.tuser.userid}" style="">
${tubuyaki.tuser.usernick}</s:link>
</font>

<font size="2" color=#999999>${tubuyaki.tuser.username}</font><br>

<p id="">${f:br(tubuyaki.murmur)}</p><br>
<font color=#808080 size="2">
<fmt:formatDate value="${tubuyaki.dateTime}"pattern="yyyy年MM月dd日 HH時mm分ss秒"/></font>

<!-- 自分のつぶやきじゃない場合リツイートと返信をつける -->
<c:if test="${fFlag==0 && tubuyaki.tuser.userid!=mine}">
<s:link href="#">リツイート</s:link>
<s:form style="margin: 0px; float: left;">
<input type="submit" value="返信"/>
</s:form>
</c:if>

<!-- 自分のつぶやきだった場合削除リンクをつける -->
<c:if test="${tubuyaki.tuser.userid==mine}">
<s:form style="margin:0px; float:left">
<input type="submit" id="deletesubmit" style="${tubuyaki.murmurid}" value="削除"/>
</s:form>
</c:if>
</div>


</td>
</tr>
</c:forEach>
</table>

</c:if>

<!-- ***********ユーザデータ*********** -->
<table>
<tr>
<td colspan="3"><h3>${mydata.usernick }</h3></td>
</tr>

<!-- ユーザ情報 -->
<tr>

<td>${mydata.follow }<br><s:link href="/followlist/followpage/${mydata.userid}" style="text-decoration: none">フォロー<br>している</s:link></td>
<td>${mydata.followed }<br><s:link href="/followlist/followedlist/${mydata.userid}" style="text-decoration: none">フォロー<br>されている</s:link></td>
<td>${mydata.postNum }<br><s:link href="showdata/${mydata.userid}" style="text-decoration: none">投稿数</s:link></td>

</tr>
</table>
<!-- ***********ページング*********** -->
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