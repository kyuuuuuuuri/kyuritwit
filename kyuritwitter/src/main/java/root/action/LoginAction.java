package root.action;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;
import org.seasar.struts.util.ResponseUtil;

import root.dto.UserDto;
import root.entity.Tuser;
import root.form.LoginForm;
//import root.form.UserForm;

public class LoginAction {

	//private final String loginPageJsp = "loginpage.jsp";

	@ActionForm
	@Resource
	protected LoginForm loginform;
	@ActionForm
	//@Resource
	//protected UserForm userform;
	@Resource
	protected UserDto userDto;

	public String greeting;

	JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");

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

    	String userName=loginform.UserName;
    	String pass=loginform.Pass;

    	//ユーザが存在するか検証
    	Tuser result =
    		jdbcManager
    		.from(Tuser.class)
    		.where("usernick = ?", userName)
    		.getSingleResult();

    	if(result==null || (!pass.equals(result.passWord))){//ユーザ名が登録されていなかったら…
    		throw new ActionMessagesException("パスワードとユーザ名が一致しません",false);
    	}

    	//ユーザＩＤをセッション登録する
    	userDto.userID=result.userid;
    	//Beans.copy(result.userid, userDto).execute();


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
    	int mine=0;
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
