package root.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import root.entity.Tuser;
import root.form.LoginForm;

import root.SuperAction;


public class LoginAction extends SuperAction{

	@ActionForm
	@Resource
	protected LoginForm loginForm;

	public String greeting;

	public int mine=0;

    @Execute(validator = false)
	public String index() {

    	return "index.jsp";
	}

    @Execute(validator = true, input="/login/index.jsp", urlPattern="main")
    public String loginSubmit(){

    	//Sessionチェック
		if(userDto.userID!=null){
			mine=userDto.userID;
			return "/main/";
		}

    	String userName=loginForm.UserName;
    	String pass=loginForm.Pass;

    	//ユーザが存在するか検証
    	Tuser result =tuserService.findByName(userName);

    	if(result==null || (!pass.equals(result.passWord))){//ユーザ名が登録されていなかったら…
    		throw new ActionMessagesException("パスワードとユーザ名が一致しません",false);
    	}

    	//ユーザＩＤをセッション登録する
    	userDto.userID=result.userid;

		return "/main?redirect=true";
    }

    //ユーザ新規登録するページへの遷移
    @Execute(validator=false)
    public String userentry(){
    	return "/user/";
    }

    //mainpageへ移動する
    @Execute(validator=false)
    public String tomain(){
    	//int mine=0;
    	if(userDto.userID!=null){
    	int userid=userDto.userID;
    	mine=userid;
    	}
		return "/main/";
    }

    //検索ページへ移動する
	@Execute(validator=false)
	public String tosearch(){
		return "/search/";
	}
	//ログインページへ移動する
	@Execute(validator=false)
	public String tologin(){
		return "index";
	}
}
