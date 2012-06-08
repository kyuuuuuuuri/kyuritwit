package root.action;

import java.util.ArrayList;
//ソート用
import java.util.List;
import java.util.regex.*;

import javax.annotation.Resource;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.Names.*;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import root.dto.UserDto;
import root.form.MainForm;
import root.service.MurmurService;
import root.entity.Follow;
import root.entity.Murmur;
import root.entity.Tuser;
import root.service.TuserService;
import root.form.AjaxForm;

public class MainAction {

	private final String mainPageJsp = "mainpage.jsp";

	@Resource
    protected MurmurService murmurService;
	@Resource
	protected TuserService tuserService;


	// JdbcManagerのインスタンスを取得
	JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");

	@ActionForm
	@Resource
	protected MainForm mainForm;

	@ActionForm
	@Resource
	protected AjaxForm ajaxform;

	@Resource
	protected UserDto userDto;

	public Tuser mydata=new Tuser();

	//前のページがあるかどうか
	public boolean hasNext = false;
	//次のページがあるかどうか
	public boolean hasPrev = false;
	//総件数
	public long total;
	//一ページに１０件ずつ表示する
	private static final int LIMIT = 10;

	public int fFlag=0;


	//自分のつぶやきかどうかを検証する変数
	public int mine=0;

	@Execute(validator = false)
	public String index(){
		return "main";
	}

	//jspファイルに渡すつぶやきリストを格納する変数
	public List<Murmur> murmurList = new ArrayList<Murmur>();

	//メインページ表示メソッド(自分のつぶやき＋他人のつぶやき
	@Execute(validator = false,urlPattern="main")
	public String main() {
		//sessionチェック
		if(userDto.userID==null){
			return "/login/";
		}

		int userid = userDto.userID;
		mine=userid;

		//ユーザ自身のデータ
		mydata = jdbcManager
		.from(Tuser.class)
		.where(eq(tuser().userid(),userid))
		.getSingleResult();


		List<Integer> murmur_userid = new ArrayList<Integer>();


		//page番号
		int page = IntegerConversionUtil.toPrimitiveInt(this.mainForm.page);


		//ユーザのフォローをリスト化
		List<Follow> followResult =
			jdbcManager
			.from(Follow.class)
			.where(eq(follow().userid(), userid))
			.getResultList();

		if(followResult!=null){
		for(Follow f : followResult){
			murmur_userid.add(f.fuserid);
		}
		}
		murmur_userid.add(userid);

		murmurList =
			jdbcManager
			.from(Murmur.class)
			.innerJoin("tuser")
			.where(new SimpleWhere().in("userid",murmur_userid.toArray()))
			.orderBy(desc("murmurid"))
			.getResultList();

		Murmur maxnew=murmurList.get(0);

		ajaxform.new_id = maxnew.murmurid;

		this.total=murmurList.size();
		/*
		 * 以下ページング処理
		 */

		//総件数を取得
		//this.total = murmurList.size();

		murmurList =
			jdbcManager
			.from(Murmur.class)
			.innerJoin("tuser")
			.where(new SimpleWhere().in("userid",murmur_userid.toArray()))
			.orderBy(desc("murmurid"))
			.limit(LIMIT)
			.offset(page*LIMIT)
			.getResultList();


		//前ページがあるかどうかを判定
		if(page != 0){
			hasPrev = true;
		}

		//次のページがあるかどうかを判定
		if((page+1)*LIMIT < total){
			hasNext = true;
		}
		return mainPageJsp;
	}


	//つぶやくメソッド
	@Execute(validator = true, input="main")
	public String ins_tubuyaki(){

		//Formに渡されたつぶやきを格納する変数
		String mur = mainForm.tubuyaki;
		//登録済みかユーザチェック
    	if(mur==null){
    		throw new ActionMessagesException("なにか入力してください", false);
    	}

		int userid=userDto.userID;

		Murmur murmur = new Murmur();

		//つぶやきにＵＲＬがあったらhtmlタグをつけてＤＢに格納する
		Pattern pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(mur);

		//もしＵＲＬパターンが見つかったらタグを入れて置換する
		if(matcher.find()){
			mur=matcher.replaceAll("<a href=\"$0\">$0</a>");
		}

		//新しいつぶやきをデータベースに格納する
		murmur.userid = userid;
		murmur.murmur=mur;
		murmur.dateTime=null;
		murmurService.insert(murmur);

		//自分の最新のつぶやきを更新する
		Tuser tuser =
			jdbcManager
			.from(Tuser.class)
			.where(eq(tuser().userid(), userid))
			.getSingleResult();
		tuser.newMur = mur;
		tuser.postNum +=1;
		tuser.newMurD =null;
		tuserService.update(tuser);


		return "main";
	}

	//ユーザ個々のつぶやきを表示する
	@Execute(validator = false, urlPattern = "showdata/{userni}")
	public String showdata(){

		fFlag=1;

		//sessionチェック
			if(userDto.userID==null){return "/login/";}

			int userid = userDto.userID;
			mine=userid;

			//ページ番号の取得
			int page = IntegerConversionUtil.toPrimitiveInt(this.mainForm.page);

			//つぶやきの件数多分ぺーじんぐ用
			long murmurListcount =
				jdbcManager
				.from(Murmur.class)
				.innerJoin("tuser")
				.where("usernick=?", mainForm.userni)
				.getCount();

			//選択されたユーザ自身のデータ
			mydata = jdbcManager
			.from(Tuser.class)
			.where(eq(tuser().usernick(),mainForm.userni))
			.getSingleResult();

		if(mydata==null){fFlag=1;return "/main";}

		/*
		 * ページング
		 */

		//総件数を取得
		this.total = murmurListcount;

		murmurList =
			jdbcManager
			.from(Murmur.class)
			.innerJoin("tuser")
			.where("userid=?", mydata.userid)
			.orderBy(desc("murmurid"))
			.limit(LIMIT)
			.offset(page * LIMIT)
			.getResultList();

		//前ページがあるかどうかを判定
		if(page != 0){
			hasPrev = true;
		}

		//次のページがあるかどうかを判定
		if((page+1)*LIMIT < total){
			hasNext = true;
		}
		return "mainpage.jsp";

	}

	//ユーザ情報を新しいウィンドウで開く
	public Tuser user_window;
	public List<Murmur> newWindoTwit = new ArrayList<Murmur>();
		//newWindows method
		@Execute(validator=false, urlPattern="newwindow/{userid1}")
		public String newwindow(){

			//newWindow user_data
			user_window = jdbcManager
			.from(Tuser.class)
			.where(eq(tuser().userid(),mainForm.userid1))
			.getSingleResult();

			//newWindo user_data twit(3)
			newWindoTwit=
				jdbcManager
				.from(Murmur.class)
				.where(eq(murmur().userid(),mainForm.userid1))
				.orderBy(desc(murmur().murmurid()))
				.limit(3)
				.offset(0)
				.getResultList();

			for(Murmur f : newWindoTwit){
				System.out.println(f.murmur);
				System.out.println("もろきゅう");
			}

			return "window.jsp";
		}


	//ログアウト
	@Execute(validator=false)
	@RemoveSession(name="userDto")
	public String logout(){
		return "/login/";
	}

}
