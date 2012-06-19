package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.SuperAction;
import root.entity.Follow;
import root.entity.Tuser;
import root.form.FollowlistForm;


public class FollowlistAction extends SuperAction{
	private final String followPageJsp = "followpage.jsp";

	@ActionForm
	@Resource
	protected FollowlistForm followlistForm;

	public Tuser tuser;

	public Tuser mydata = new Tuser();

    public List<Tuser> followList= new ArrayList<Tuser>();
    public List<Tuser> followedList = new ArrayList<Tuser>();
    public int followCheck;
    public int mine;
    public List<Integer> fc_userid=new ArrayList<Integer>();
    public long followcheckcount=0;


    @Execute(validator=false, urlPattern="followpage/{id}")
    public String followpage() {

    	//ログインしているユーザのユーザID
    	Integer userid=userDto.userID;
    	mine=userid;

    	//ユーザ自身のデータ
    	mydata =tuserService.findById(followlistForm.id);

    	List<Follow> followResult = new ArrayList<Follow>();
    	followResult =null;

    	followResult =followService.findUserFollow(followlistForm.id);

    	long followResultcount =followService.followCount(followlistForm.id);
		List<Integer> f_userid = new ArrayList<Integer>();

		if(followResultcount!=0){
    	for(Follow f : followResult){
			f_userid.add(f.fuserid);
		}

    	followList=null;

   		long followListcount=tuserService.tuserListcount(f_userid);

   		//以下、ページング処理
   		//ページ番号を取得
   		int page = IntegerConversionUtil.toPrimitiveInt(this.followlistForm.page);

   		//総件数を取得
   		this.total = followListcount;
   		//followList取得
   		followList =tuserService.tuserPager(LIMIT, page, f_userid);

   		//前ページがあるかどうかを判定
   		hasPrev = murmurService.hasPrev(page);
   		//次のページがあるかどうかを判定
   		hasNext=murmurService.hasNext(LIMIT, this.total, page);

    	return followPageJsp;
		}else{
    	return followPageJsp;}
    }

    //フォローされているユーザを表示する
    @Execute(validator=false, urlPattern="followedlist/{id}")
    public String followedlist(){

    	//ログインしているユーザのユーザID
    	int userid=userDto.userID;
    	mine = userid;
    	//ユーザ自身のデータ
    	mydata =tuserService.findById(followlistForm.id);

    	//ページ番号を取得
    	int page = IntegerConversionUtil.toPrimitiveInt(this.followlistForm.page);

    	List<Follow> followedResult =new ArrayList<Follow>();
    	followedResult=null;

    	long followedResultcount =followService.beFollowedCount(followlistForm.id);

    	List<Integer> fed_userid = new ArrayList<Integer>();

    	if(followedResultcount!=0){
    		//フォローされているユーザIDをリスト化
        	//ユーザのフォローされてるリスト
        	followedResult =followService.beFollowedList(followlistForm.id);

    		for(Follow f : followedResult){fed_userid.add(f.userid);}

    		if(userid==followlistForm.id){//もし自分自身だったら

    				List<Follow> followcheck =followService.findUserFollow(userid);

    				//自分がフォローしているユーザを抜き出す
    				followcheckcount =followService.followCount(userid);

    				if(followcheckcount!=0){
    			    	for(Follow c : followcheck){
    						fc_userid.add(c.fuserid);
    					}
    				}
    			}

    	followedList=null;

    	//フォローされた人数
    	long followedListcount =tuserService.tuserListcount(fed_userid);


    	// ページング処理
    	//総件数を取得
    	this.total = followedListcount;

    	followedList = tuserService.tuserPager(LIMIT, page, fed_userid);

    	//前ページがあるかどうかを判定
    	hasPrev = murmurService.hasPrev(page);
    	//次のページがあるかどうかを判定
    	hasNext=murmurService.hasNext(LIMIT, this.total, page);

    	}else{
    		followedList=null;
    		}
    	return "followedpage.jsp";
    }

    //フォローを削除
    @Execute(validator=false, urlPattern="unfollow/{fuserid}")
    public String unfollow(){

    	int userid=userDto.userID;
    	int delete_userID = followlistForm.fuserid;
    	mine = userid;

    	Follow delResult =followService.delFollow(delete_userID, userid);

    	followService.delete(delResult);

    	//ユーザのデータを変える
    	//フォロー数を削除
    	Tuser tuser = tuserService.findById(userid);
    	tuser.follow -=1;
    	tuserService.update(tuser);

    	//フォロワ―数を削除
    	Tuser tuser2 = tuserService.findById(delete_userID);
    	tuser2.followed-=1;
    	tuserService.update(tuser2);

    	String list="followpage/"+userid;
    	return list;
	    }


    //フォローを追加する
    @Execute(validator=false, urlPattern="follownow/{fuserid}")
    public String follownow(){
    	int userid= userDto.userID;

    	int insert_userID = followlistForm.fuserid;

    	//フォローしていなかったフォロワ―をinsert
    	Follow foluser = new Follow();
    	foluser.userid=userid;
    	foluser.fuserid=followlistForm.fuserid;
    	followService.insert(foluser);

    	//フォロー数を更新
    	Tuser tuser = tuserService.findById(userid);
    	tuser.follow +=1;
    	tuserService.update(tuser);

    	//フォロワ―数を更新
    	Tuser tuser2 = tuserService.findById(insert_userID);
    	tuser2.followed+=1;
    	tuserService.update(tuser2);

    	String list = "followedlist/" + userid;

    	return list;
    }

}
