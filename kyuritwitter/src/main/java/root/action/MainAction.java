package root.action;

import java.util.ArrayList;
//ソート用
import java.util.List;
import java.util.regex.*;

import javax.annotation.Resource;

import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.exception.ActionMessagesException;

import root.SuperAction;

import root.entity.Follow;
import root.entity.Murmur;
import root.entity.Tuser;
import root.form.MainForm;


public class MainAction extends SuperAction{

	private final String mainPageJsp = "mainpage.jsp";

	@ActionForm
	@Resource
	protected MainForm mainForm;

	public Tuser mydata=new Tuser();

	public int fFlag=0;

	@Execute(validator = false)
	public String index(){
		return "main";
	}

	//jspファイルに渡すつぶやきリストを格納する変数
	public List<Murmur> murmurList = new ArrayList<Murmur>();

	//メインページ表示メソッド(自分のつぶやき＋他人のつぶやき
	@Execute(validator = false,urlPattern="main")
	public String main() {

		int userid = userDto.userID;
		mine=userid;

		//ユーザ自身のデータ
		mydata=tuserService.findById(userid);

		List<Integer> murmur_userid = new ArrayList<Integer>();

		//page番号
		int page = IntegerConversionUtil.toPrimitiveInt(this.mainForm.page);

		//ユーザのフォローをリスト化
		List<Follow> followResult =followService.findUserFollow(userid);

		if(followResult!=null){
		for(Follow f : followResult){
			murmur_userid.add(f.fuserid);
		}
		}
		murmur_userid.add(userid);

//		Murmur maxnew=murmurList.get(0);
//		ajaxform.new_id = maxnew.murmurid;

		this.total=murmurService.listCount(murmur_userid);

		// 以下ページング処理
		murmurList =murmurService.mainListPager(LIMIT,page,murmur_userid);
		//前ページがあるかどうかを判定
		hasPrev=murmurService.hasPrev(page);
		//次のページがあるかどうかを判定
		hasNext = murmurService.hasNext(LIMIT,this.total,page);

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
		Tuser tuser = tuserService.findById(userid);
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

			int userid = userDto.userID;
			mine=userid;

			//ページ番号の取得
			int page = IntegerConversionUtil.toPrimitiveInt(this.mainForm.page);

			//選択されたユーザ自身のデータ
			mydata =tuserService.findByName(mainForm.userni);
			//System.out.println(mydata.userid+"きゅうり");

		if(mydata==null){fFlag=0;return "/main";}

		//ページング
		//総件数を取得
		this.total = murmurService.Count(mydata.userid);

		murmurList =murmurService.listPager(LIMIT, page, mydata.userid);

		//前ページがあるかどうかを判定
		hasPrev=murmurService.hasPrev(page);
		//次のページがあるかどうかを判定
		hasNext = murmurService.hasNext(LIMIT,total,page);

		return "mainpage.jsp";

	}

	//ユーザ情報を新しいウィンドウで開く
	public Tuser user_window;
	public List<Murmur> newWindoTwit = new ArrayList<Murmur>();
	//newWindows method
	@Execute(validator=false, urlPattern="newwindow/{userid1}")
	public String newwindow(){

		//newWindow user_data
		user_window =tuserService.findById(mainForm.userid1);

		//newWindo user_data twit(3)
		newWindoTwit=murmurService.listPager(3,0,mainForm.userid1);

		return "window.jsp";
	}

}
