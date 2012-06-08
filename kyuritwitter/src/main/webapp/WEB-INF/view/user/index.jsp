<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript">

<html:javascript formName="userActionForm_account"/>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="${f:url('/js/jquery-1.3.2.min.js')}"></script>
<script type="text/javascript" src="${f:url('/js/password_strength.min.js')}"></script>


<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/cssfile.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css" />
<link rel="Stylesheet" href="${pageContext.request.contextPath}/css/style.css" />


<style type="text/css">
input.style{
width:200;
height:3em;
}
input.style1{
width:200;
height:3em;
}
</style>

<script type="text/javascript">
	$(function(){

		$(".style1").password({
			score:'.pass_st'//強度表示するclass
		});

		$("#open").click(function(){
			$("div#user_reg").slideDown("slow");
		});

		$("#close").click(function(){
			$("div#user_reg").slideUp("slow");
		});
		$("#toggle a").click(function(){
			$("#toggle a").toggle();
		});
	});

</script>

<title>ユーザ登録</title>

<tiles:insert page="/WEB-INF/view/common/header.jsp"  />
</head>
<body>
<br>
<br>
<div id="baroon">
<div id="balloon-p1"></div>
	<div id="balloon-p2"></div>
	<div id="balloon-c">
<font size="6">ツイッターに参加しましょう</font>
<font size="2">もうツイッターに登録してますか<s:link href="../login">ログイン</s:link></font>
<br>
<br>
<br>
<!-- ユーザ登録画面をスライドで表示する -->
		<!--  <ul class="reg"> -->
				<div id="toggle">
				<a id="open" class="open" href="#">you can make your account</a>
				<a id="close" style="display: none;" class="close" href="#">Close</a>
				</div>

<s:form>
	<html:errors/>
	<div id="user_reg">
	<label class="w1" for="username">名前:</label>
	<input type="text" name="userName" class="style" id="username"/>
	<br>
	<label class="w1" for="usernick">ユーザ名:</label>
	<input type="text" name="userNick" class="style" id="usernick"/>
	<br>
	<label class="w1" for="myinput">パスワード:</label>
	<input type="password" class = "style1"  id="myinput" name="pass" />
	<p class="pass_st" ></p>
	<br>
	<label class="w1" for="checkpass">パスワード確認:</label>
	<input type="password" name="checkPass" class="style" id="checkpass"/>
	<br>
	<label class="w1" for="mail">メールアドレス:</label>
	<input type="text" name="mailAd" class="style" id="mail"/>
	<br>
	<div><input type="submit" name="account" id="account" value="アカウントを作成する"></div>
	</div>
</s:form>
</div>
</div>

</body>
</html>

