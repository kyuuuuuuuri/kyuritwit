window.onload=$(function(){

//	var myTimer=setInterval(myFunc, 5000);

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

	//＠のついた文字列をリンク付けする
	$('p').each(function(){
		var text=$(this).text();
		txt = $(this).text().replace (/(＠)([A-Za-z0-9]{4,20})/, '<a href="showdata/$2">$1$2</a>');
		$(this).html(txt);
	});

});

function OpenWin(index){
		var selecter;
		selecter="#"+ index;
		var userid=$(selecter).attr("style");
		userid="newwindow/"+userid;
		win=window.open(userid,"new","width=400,height=400, menubar=no, toolbar=no, location=no");
		win.moveTo(50,50);
}

//replyanメソッド
function replyan(usernick){
	var selecter;
	selecter="#"+ usernick;
	var nick="＠"+$(selecter).text()+"　";
	$("input[name='tubuyaki']").val(nick).css("color", "#000");
	$("input[name='tubuyaki']").focus();
}

