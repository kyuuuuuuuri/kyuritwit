package root.action.main;

import static org.seasar.extension.jdbc.operation.Operations.desc;
import static org.seasar.extension.jdbc.operation.Operations.eq;
import static root.entity.Names.follow;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import root.entity.Follow;
import root.entity.Murmur;
import root.dto.UserDto;
import root.form.AjaxForm;

public class HelloAction {


	public String greeting;
	@Resource
	protected UserDto userDto;

	@ActionForm
	@Resource
	protected AjaxForm ajaxform;

	@Execute(validator = false)
	public String index() {

		// JdbcManagerのインスタンスを取得
		JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");
		int userid = userDto.userID;
		List<Murmur> murmurList = new ArrayList<Murmur>();
		List<Integer> murmur_userid = new ArrayList<Integer>();

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

		murmurList = jdbcManager
		.from(Murmur.class)
		.innerJoin("tuser")
		.where(new SimpleWhere().in("userid",murmur_userid.toArray()))
		.orderBy(desc("murmurid"))
		.getResultList();

		Murmur maxnew=murmurList.get(0);

		int may_newid = maxnew.murmurid;

		if(may_newid!=ajaxform.new_id){
			ResponseUtil.write("<s:link href='/main/'>タイムラインが更新されました</s:link>");
		}


	    return null;
	}
}
