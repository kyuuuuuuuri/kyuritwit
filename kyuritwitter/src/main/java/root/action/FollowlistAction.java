package root.action;

import java.util.ArrayList;
import java.util.List;


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

import root.dto.MurmurDto;
import root.dto.UserDto;
import root.entity.Follow;
import root.entity.Tuser;
import root.form.FollowlistForm;
import root.service.FollowService;
import root.service.TuserService;


public class FollowlistAction {
	private final String followPageJsp = "followpage.jsp";


	@Resource
	protected UserDto userDto;
	@Resource
	@ActionForm
	protected FollowlistForm followlistForm;
	@Resource
    protected FollowService followService;
	@Resource
	protected TuserService tuserService;

	//public Map sessionScope;

	//UserDto userDto = (UserDto)sessionScope.get("userDto");


	public Tuser tuser;

	//前のページがあるかどうか
	public boolean hasNext = false;
	//次のページがあるかどうか
	public boolean hasPrev = false;
	//総件数
	public long total;
	//一ページに１０件ずつ表示するのだ
	private static final int LIMIT = 10;

	// JdbcManagerのインスタンスを取得
    JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");
    public Tuser mydata = new Tuser();

    public List<Tuser> followList= new ArrayList<Tuser>();
    public List<Tuser> followedList = new ArrayList<Tuser>();
    public int followCheck;
    public int mine;
    public List<Integer> fc_userid=new ArrayList<Integer>();
    //public List<Follow> followcheck=new ArrayList<Follow>();
    public long followcheckcount=0;


    @Execute(validator=false, urlPattern="followpage/{id}")
    public String followpage() {

    	//セッションチェック
    	if(userDto.userID==null){
    		return "/login/loginpage.jsp";
    	}

    	//ログインしているユーザのユーザID
    	Integer userid=userDto.userID;
    	mine=userid;

    	//ユーザ自身のデータ
    	mydata = jdbcManager
    	.from(Tuser.class)
    	.where(eq(tuser().userid(),followlistForm.id))
    	.getSingleResult();

    	List<Follow> followResult = new ArrayList<Follow>();
    	followResult =null;

    	followResult =
    		jdbcManager
    		.from(Follow.class)
    		.where(eq(follow().userid(),followlistForm.id))
    		.getResultList();

    	long followResultcount =
        		jdbcManager
        		.from(Follow.class)
        		.where(eq(follow().userid(),followlistForm.id))
        		.getCount();

		List<Integer> f_userid = new ArrayList<Integer>();

		if(followResultcount!=0){
    	for(Follow f : followResult){
			f_userid.add(f.fuserid);
		}

    	followList=null;

    		long followListcount =
    			jdbcManager
    			.from(Tuser.class)
    			.where(new SimpleWhere().in("userid", f_userid))
    			.getCount();

    		/*
    		 * 以下、ページング処理
    		 */
    		//ページ番号を取得
    		int page = IntegerConversionUtil.toPrimitiveInt(this.followlistForm.page);

    		//総件数を取得
    		this.total = followListcount;


    		followList =
				jdbcManager
				.from(Tuser.class)
				.where(new SimpleWhere().in("userid", f_userid))
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
    	mydata = jdbcManager
    	.from(Tuser.class)
    	.where(eq(tuser().userid(),followlistForm.id))
    	.getSingleResult();


    	//ページ番号を取得
    	int page = IntegerConversionUtil.toPrimitiveInt(this.followlistForm.page);

    	List<Follow> followedResult =new ArrayList<Follow>();
    	followedResult=null;

    	long followedResultcount =
        		jdbcManager
        		.from(Follow.class)
        		.where(eq(follow().fuserid(),followlistForm.id))
        		.getCount();

    	List<Integer> fed_userid = new ArrayList<Integer>();

    	//System.out.println("きゅうり"+followedResultcount);

    	if(followedResultcount!=0){

    		//フォローされているユーザIDをリスト化
        	//ユーザのフォローされてるリスト
        	followedResult =
        		jdbcManager
        		.from(Follow.class)
        		.where(eq(follow().fuserid(),followlistForm.id))
        		.getResultList();

    		for(Follow f : followedResult){
    			fed_userid.add(f.userid);}

    		if(userid==followlistForm.id){//もし自分自身だったら

    				List<Follow> followcheck =
    		    		jdbcManager
    		    		.from(Follow.class)
    		    		.where(eq(follow().userid(),userid))
    		    		.getResultList();

    				//自分がフォローしているユーザを抜き出す
    				followcheckcount =
        		    		jdbcManager
        		    		.from(Follow.class)
        		    		.where(eq(follow().userid(),userid))
        		    		.getCount();

    				if(followcheckcount!=0){
    			    	for(Follow c : followcheck){
    						fc_userid.add(c.fuserid);
    					}
    				}
    				for(Integer a:fc_userid){
    					System.out.println("きゅうり"+a+"きゅうり");
    				}
    			}



    	followedList=null;

    	//フォローされた人数
    	long followedListcount =
    			jdbcManager
    			.from(Tuser.class)
    			.where(new SimpleWhere().in("userid", fed_userid))
    			.getCount();

    	/**
    	 * ページング処理
    	 */
    	//総件数を取得
    	this.total = followedListcount;

    	followedList =
			jdbcManager
			.from(Tuser.class)
			.where(new SimpleWhere().in("userid", fed_userid))
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
    	}else{
    	followedList=null;}
    	return "followedpage.jsp";
    }


	    //フォローを削除
	    @Execute(validator=false, urlPattern="unfollow/{fuserid}")
	    public String unfollow(){

	    	int userid=userDto.userID;
	    	int delete_userID = followlistForm.fuserid;
	    	mine = userid;

	    	Follow delResult =
	    		jdbcManager
	    		.from(Follow.class)
	    		.where(
	    				and(
	    						eq(follow().fuserid(), delete_userID),
	    						eq(follow().userid(), userid))
	    		)
	    		.getSingleResult();

	    	followService.delete(delResult);

	    	//ユーザのデータを変える
	    	//フォロー数を削除
    		Tuser tuser =
    			jdbcManager
    			.from(Tuser.class)
    			.where(eq(tuser().userid(),userid))
    			.getSingleResult();
    		tuser.follow -=1;
    		tuserService.update(tuser);


    		//フォロワ―数を削除
    		Tuser tuser2 =
    			jdbcManager
    			.from(Tuser.class)
    			.where(eq(tuser().userid(),delete_userID))
    			.getSingleResult();
    		tuser2.followed-=1;
    		tuserService.update(tuser2);



    		//削除後のフォローリストを表示する
    		List<Follow> followResult = new ArrayList<Follow>();
        	followResult =null;

        	followResult =
        		jdbcManager
        		.from(Follow.class)
        		.where(eq(follow().userid(),userid))
        		.getResultList();

        	long followResultcount =
            		jdbcManager
            		.from(Follow.class)
            		.where(eq(follow().userid(),userid))
            		.getCount();

        	followList=null;

        	List<Integer> f_userid = new ArrayList<Integer>();

    		if(followResultcount!=0){
        	for(Follow f : followResult){
        		f_userid.add(f.fuserid);
    		}

        		long followListcount =
        			jdbcManager
        			.from(Tuser.class)
        			.where(new SimpleWhere().in("userid", f_userid))
        			.getCount();

        		/*
        		 * 以下、ページング処理
        		 */
        		//ページ番号を取得
        		int page = IntegerConversionUtil.toPrimitiveInt(this.followlistForm.page);

        		//総件数を取得
        		this.total = followListcount;


        		followList =
    				jdbcManager
    				.from(Tuser.class)
    				.where(new SimpleWhere().in("userid", f_userid))
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

        	//ユーザ自身のデータ
        	mydata = jdbcManager
        	.from(Tuser.class)
        	.where(eq(tuser().userid(),userid))
        	.getSingleResult();
    		}


	    	return "followpage.jsp";
	    }


	    //フォローを追加する
	   @Execute(validator=false, urlPattern="follownow/{fuserid}")
	   public String follownow(){
		   int userid= userDto.userID;

		   int insert_userID = followlistForm.fuserid;

	    	//すでにフォローしていたら何もしない
	    	Follow fol =
	    		jdbcManager
	    	.from(Follow.class)
	    	.where(
	    			and(
	    					eq(follow().fuserid(), insert_userID),
	    					eq(follow().userid(), userid))
	    	)
	    	.getSingleResult();

	    	if(fol!=null){
	    		List<Follow> followedResult = new ArrayList<Follow>();
	        	followedResult =null;

	        	followedResult =
	        		jdbcManager
	        		.from(Follow.class)
	        		.where(eq(follow().fuserid(),userid))
	        		.getResultList();

	        	long followedResultcount =
	            		jdbcManager
	            		.from(Follow.class)
	            		.where(eq(follow().fuserid(),userid))
	            		.getCount();

	        	followList=null;

	        	List<Integer> f_userid = new ArrayList<Integer>();

	    		if(followedResultcount!=0){
	        	for(Follow f : followedResult){
	        		f_userid.add(f.userid);
	    		}

	        		long followedListcount =
	        			jdbcManager
	        			.from(Tuser.class)
	        			.where(new SimpleWhere().in("userid", f_userid))
	        			.getCount();

	        		/*
	        		 * 以下、ページング処理
	        		 */
	        		//ページ番号を取得
	        		int page = IntegerConversionUtil.toPrimitiveInt(this.followlistForm.page);

	        		//総件数を取得
	        		this.total = followedListcount;


	        		followedList =
	    				jdbcManager
	    				.from(Tuser.class)
	    				.where(new SimpleWhere().in("userid", f_userid))
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
	    		}
	    		 //ユーザ自身のデータ
	 		   mydata = jdbcManager
	 				   .from(Tuser.class)
	 				   .where(eq(tuser().userid(),userid))
	 				   .getSingleResult();
	    		return "followedpage.jsp";
	    	}
	    	//フォローしていなかったフォロワ―をinsert

	    		Follow foluser = new Follow();
	    		foluser.userid=userid;
	    		foluser.fuserid=followlistForm.fuserid;

	    		followService.insert(foluser);


	    	//フォロー数を更新
	    		Tuser tuser =
	    			jdbcManager
	    			.from(Tuser.class)
	    			.where(eq(tuser().userid(),userid))
	    			.getSingleResult();
	    		tuser.follow +=1;
	    		tuserService.update(tuser);


			//フォロワ―数を更新
	    		Tuser tuser2 =
	    			jdbcManager
	    			.from(Tuser.class)
	    			.where(eq(tuser().userid(),followlistForm.fuserid))
	    			.getSingleResult();
	    		tuser2.followed+=1;
	    		tuserService.update(tuser2);

	    		//追加後のフォロワーリストを表示する
	    		List<Follow> followedResult = new ArrayList<Follow>();
	        	followedResult =null;

	        	followedResult =
	        		jdbcManager
	        		.from(Follow.class)
	        		.where(eq(follow().fuserid(),userid))
	        		.getResultList();

	        	long followedResultcount =
	            		jdbcManager
	            		.from(Follow.class)
	            		.where(eq(follow().fuserid(),userid))
	            		.getCount();

	        	followList=null;

	        	List<Integer> f_userid = new ArrayList<Integer>();

	    		if(followedResultcount!=0){
	        	for(Follow f : followedResult){
	        		f_userid.add(f.userid);
	    		}

	        		long followedListcount =
	        			jdbcManager
	        			.from(Tuser.class)
	        			.where(new SimpleWhere().in("userid", f_userid))
	        			.getCount();

	        		/*
	        		 * 以下、ページング処理
	        		 */
	        		//ページ番号を取得
	        		int page = IntegerConversionUtil.toPrimitiveInt(this.followlistForm.page);

	        		//総件数を取得
	        		this.total = followedListcount;


	        		followedList =
	    				jdbcManager
	    				.from(Tuser.class)
	    				.where(new SimpleWhere().in("userid", f_userid))
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
	    		}
	    		 //ユーザ自身のデータ
	 		   mydata = jdbcManager
	 				   .from(Tuser.class)
	 				   .where(eq(tuser().userid(),userid))
	 				   .getSingleResult();

		   return "followedpage.jsp";
	   }


	   private static final String MurMur_DTO =
	    	" select murmur.*, tuser.username, tuser.usernick"
			+ " from murmur inner join tuser"
			+ " on murmur.userid = tuser.userid"
			+ " where murmur.userid = ? "
			+ " order by murmur.murmurid desc" ;



	   public List<MurmurDto> murmurList= new ArrayList<MurmurDto>();

	   //ユーザ(特定一人)のつぶやきを表示する
	   @Execute(validator=false, urlPattern="showfollowdata/{fuserid}")
	   public String showfollowdata(){

		//セッションチェック
		   if(userDto.userID==null){
			   return "/login/";
		   }

		int show_userID = followlistForm.fuserid;

		//ページ番号を取得
		int page = IntegerConversionUtil.toPrimitiveInt(this.followlistForm.page);

		//ユーザのを検索


		//ユーザのつぶやきリストを制作
		murmurList =
			jdbcManager
			.selectBySql(MurmurDto.class, MurMur_DTO, show_userID)
			.getResultList();

		   /*
		    * 以下、ページング処理
		    */

		   //総件数を取得
		   this.total = murmurList.size();

		 //ユーザのつぶやきリストを制作
		   murmurList =
				jdbcManager
				.selectBySql(MurmurDto.class, MurMur_DTO, show_userID)
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

		   return "showfollowdata.jsp";
	}


	//ログアウト
	@Execute(validator=false)
	@RemoveSession(name="userDto")
	public String logout(){
		return "/login/";
	}


}
