package root.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.util.IntegerConversionUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.SuperAction;
import root.entity.Tuser;
import root.entity.Follow;
import root.form.SearchForm;

public class SearchAction extends SuperAction{

	private final String SearchPageJsp = "searchpage.jsp";
	private final String SearchResultJsp = "searchResult.jsp";

	@ActionForm
	@Resource
	protected SearchForm searchForm;

    public int followCheck;

	public int mine;
	public long followcheckcount=0;
	public List<Integer> fc_userid = new ArrayList<Integer>();
	public String search;

    @Execute(validator = false)
	public String index() {
    	mine=userDto.userID;
        return SearchPageJsp;
	}

    public List<Tuser> searchUser = new ArrayList<Tuser>();

    //検索処理
    @Execute(validator = false)
	public String searchSubmit() {
    	int userid=userDto.userID;
    	mine=userid;

    	//ページ番号を取得
    	int page = IntegerConversionUtil.toPrimitiveInt(this.searchForm.page);
    	search=searchForm.search;
    	searchUser = tuserService.tuserSerch(search);

    	List<Integer> fed_userid = new ArrayList<Integer>();

    	if(searchUser != null){

    		for(Tuser f : searchUser){
    			fed_userid.add(f.userid);
    		}

    		//ログインユーザは、そのユーザをフォローしているか検証
    		List<Follow> followcheck = followService.findUserFollow(userid);

    		followcheckcount = followService.followCount(userid);

    		if(followcheckcount!=0){
    			for(Follow c : followcheck){
    				fc_userid.add(c.fuserid);
    			}
    		}

    		//以下、ページング処理
    		//総件数を取得
    		this.total = searchUser.size();

    		searchUser = tuserService.tuserSerch(LIMIT, page, search);

    		//前ページがあるかどうかを判定
    		hasPrev = murmurService.hasPrev(page);
    		//次のページがあるかどうかを判定
    		hasNext=murmurService.hasNext(LIMIT, this.total, page);
    	}
    	return SearchResultJsp;
	}

    //検索したユーザをフォローする
    @Execute(validator = false , urlPattern="infollow/{userid}")
    public String infollow(){

    	int userid= userDto.userID;

    	//すでにフォローしていたら何もしない
    	Follow fol = followService.delFollow(searchForm.userid, userid);

    	if(fol!=null){
    		return "searchSubmit";
    	}else{

    	//フォローしていなかったらフォロワ―をinsertする…。

    	Follow foluser = new Follow();
    	foluser.userid=userid;
    	foluser.fuserid=searchForm.userid;

    	followService.insert(foluser);

    	//フォロー数を更新
    	Tuser tuser = tuserService.findById(userid);
		tuser.follow +=1;
		tuserService.update(tuser);

		//フォロワ―数を更新
		Tuser tuser2 = tuserService.findById(searchForm.userid);
		tuser2.followed+=1;
		tuserService.update(tuser2);

		//ページ番号を取得
    	int page = IntegerConversionUtil.toPrimitiveInt(this.searchForm.page);

    	searchUser = tuserService.tuserSerch(searchForm.search);

    	List<Integer> fed_userid = new ArrayList<Integer>();

    	if(searchUser != null){
    		for(Tuser f : searchUser){
    			fed_userid.add(f.userid);
    		}

        	//ログインユーザは、そのユーザをフォローしているか検証
    		List<Follow> followcheck = followService.findUserFollow(userid);

    		followcheckcount = followService.followCount(userid);

    		if(followcheckcount!=0){
    			for(Follow c : followcheck){
    				fc_userid.add(c.fuserid);
    			}
    		}

		    //ページング処理
    		//総件数を取得
    		this.total = searchUser.size();

    		searchUser = tuserService.tuserSerch(LIMIT, page, searchForm.search);

    		//前ページがあるかどうかを判定
    		hasPrev = murmurService.hasPrev(page);
    		//次のページがあるかどうかを判定
    		hasNext=murmurService.hasNext(LIMIT, this.total, page);
    	}

    	return "searchResult.jsp";
    	}
    }

}
