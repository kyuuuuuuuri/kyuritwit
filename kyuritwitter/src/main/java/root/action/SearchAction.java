package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.Names.*;

//import root.dto.MurmurDto;
import root.dto.UserDto;
import root.entity.Tuser;
import root.entity.Follow;
import root.form.SearchForm;
import root.service.FollowService;
import root.service.TuserService;


public class SearchAction {

	private final String SearchPageJsp = "searchpage.jsp";
	private final String SearchResultJsp = "searchResult.jsp";

	// JdbcManagerのインスタンスを取得
    JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");


    @ActionForm
    @Resource
    protected SearchForm searchForm;
    @Resource
    protected UserDto userDto;
    @Resource
    protected FollowService followService;
    @Resource
    protected TuserService tuserService;

    public int followCheck;

	//前のページがあるかどうか
	public boolean hasNext = false;
	//次のページがあるかどうか
	public boolean hasPrev = false;
	//総件数
	public long total;
	//一ページに１０件ずつ表示するのだ
	private static final int LIMIT = 10;
	public int mine;
	public long followcheckcount=0;
	public List<Integer> fc_userid = new ArrayList<Integer>();
	public String search;

    @Execute(validator = false)
	public String index() {
    	//sessionチェック
    	if(userDto.userID==null){
    		return "/login/";
    	}
    	mine=userDto.userID;
        return SearchPageJsp;
	}

    public List<Tuser> searchUser = new ArrayList<Tuser>();

    //検索処理
    @Execute(validator = false)
	public String searchSubmit() {
    	//sessionチェック
    	if(userDto.userID==null){
    		return "/login/";
    	}
    	int userid=userDto.userID;
    	mine=userid;
    	//ページ番号を取得
    	int page = IntegerConversionUtil.toPrimitiveInt(this.searchForm.page);
    	search=searchForm.search;
    	searchUser =
    		jdbcManager
    		.from(Tuser.class)
    		.where(
    				or(
    						like(tuser().username(), searchForm.search),
    						like(tuser().usernick(), searchForm.search)
    				)
    		)
    		.getResultList();

    	List<Integer> fed_userid = new ArrayList<Integer>();

    	if(searchUser != null){

    		for(Tuser f : searchUser){
    			fed_userid.add(f.userid);
    		}

    		//ログインユーザは、そのユーザをフォローしているか検証
    		List<Follow> followcheck =
		    		jdbcManager
		    		.from(Follow.class)
		    		.where(eq(follow().userid(),userid))
		    		.getResultList();

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

		   /*
		    * 以下、ページング処理
		    */
		   //総件数を取得
		   this.total = searchUser.size();

		   searchUser =
	    		jdbcManager
	    		.from(Tuser.class)
	    		.where(
	    				or(
	    						like(tuser().username(), searchForm.search),
	    						like(tuser().usernick(), searchForm.search)
	    				)
	    		)
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
        return SearchResultJsp;
	}


    /*private static final String MurMur_DTO =
    	" select murmur.*, tuser.*"
		+ " from murmur inner join tuser"
		+ " on murmur.userid = tuser.userid"
		+ " where murmur.userid = ? "
		+ " order by murmur.murmurid desc" ;*/

    //ユーザのつぶやきを見る
    //public List<MurmurDto> userdata = new ArrayList<MurmurDto>();


    //検索したユーザをフォローする
    @Execute(validator = false , urlPattern="infollow/{userid}")
    public String infollow(){

    	int userid= userDto.userID;

    	//すでにフォローしていたら何もしない
    	Follow fol =
    		jdbcManager
    		.from(Follow.class)
    		.where(
    				and(
 	   						eq(follow().fuserid(), searchForm.userid),
 	   						eq(follow().userid(), userid))
    		)
    	.getSingleResult();

    	if(fol!=null){
    		return "searchSubmit";
    	}else{

    	//フォローしていなかったらフォロワ―をinsertする…。

    	Follow foluser = new Follow();
    	foluser.userid=userid;
    	foluser.fuserid=searchForm.userid;

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
			.where(eq(tuser().userid(),searchForm.userid))
			.getSingleResult();
		tuser2.followed+=1;
		tuserService.update(tuser2);

		//ページ番号を取得
    	int page = IntegerConversionUtil.toPrimitiveInt(this.searchForm.page);

    	searchUser =
    		jdbcManager
    		.from(Tuser.class)
    		.where(
    				or(
    						like(tuser().username(), searchForm.search),
    						like(tuser().usernick(), searchForm.search)
    				)
    		)
    		.getResultList();

    	List<Integer> fed_userid = new ArrayList<Integer>();

    	if(searchUser != null){

    		for(Tuser f : searchUser){
    			fed_userid.add(f.userid);
    		}

    		//ログインユーザは、そのユーザをフォローしているか検証
    		/*long followcheck =
        			jdbcManager
        			.from(Follow.class)
        			.where(
        					and(new SimpleWhere().in("fuserid",fed_userid),
        							new SimpleWhere().eq(follow().userid(),userid))
        							)
        			.getCount();

        	if(followcheck==0){
        		followCheck=1;
        	}
    		 */
        	//ログインユーザは、そのユーザをフォローしているか検証
    		List<Follow> followcheck =
		    		jdbcManager
		    		.from(Follow.class)
		    		.where(eq(follow().userid(),userid))
		    		.getResultList();

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
		   /*
		    * 以下、ページング処理
		    */
		   //総件数を取得
		   this.total = searchUser.size();

		   searchUser =
	    		jdbcManager
	    		.from(Tuser.class)
	    		.where(
	    				or(
	    						like(tuser().username(), searchForm.search),
	    						like(tuser().usernick(), searchForm.search)
	    				)
	    		)
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

		return "searchResult.jsp";
    	}
    }


	@Execute(validator=false)
	@RemoveSession(name="userDto")
	public String logout(){

		return "/login/";
	}

}
