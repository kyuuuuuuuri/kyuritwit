package root.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import root.entity.Tuser;
import root.form.UserForm;

import root.SuperAction;


public class UserAction extends SuperAction{

	@ActionForm
	@Resource
	public UserForm userForm;

    private final String userIndexJsp = "userInput.jsp";

    public int mine=0;

    @Execute(validator=false)
    public String index() {
    	return "index.jsp";
    }

    //jspファイルに渡すtuser
    public Tuser tuser = new Tuser();

    //ユーザ登録で入力されたものを取得して処理する
    @Execute(validator=true,input=userIndexJsp)
    public String account(){
    	String pass = userForm.pass;
    	String checkPass = userForm.checkPass;
    	String userNick = userForm.userNick;
    	String userName = userForm.userName;

    	//すでに使われているユーザ名なのか検索
    	Tuser result =tuserService.findByName(userNick);

    	//登録済みかユーザチェック
    	if(result!=null){
    		throw new ActionMessagesException("入力したユーザはすでに存在しています", false);
    	}
    	//パスワードとパスワード(確認)が一致しているかをチェック
    	if(!pass.equals(checkPass)){
    		throw new ActionMessagesException("パスワードが一致しません。確認してください。", false);
    	}

    	tuser.username = userName;
    	tuser.usernick = userNick;
    	tuser.mailad = userForm.mailAd;
    	tuser.passWord = pass;
    	tuser.follow=0;
    	tuser.followed=0;
    	tuser.skey=0;
    	tuser.postNum=0;
    	tuserService.insert(tuser);

    	return "entrypage.jsp";

	}

	//エントリーページへ遷移する
	@Execute(validator=false)
	public String entrypage(){
		return "entrypage.jsp";
	}

	//ログインページへ遷移する
	@Execute(validator=false)
	public String login(){
		return "../login/index.jsp";
	}

}
