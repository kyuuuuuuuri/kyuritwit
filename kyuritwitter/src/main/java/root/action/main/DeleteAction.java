package root.action.main;

import static org.seasar.extension.jdbc.operation.Operations.eq;

import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;

import static root.entity.Names.tuser;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.entity.Murmur;
import root.entity.Tuser;
import root.dto.UserDto;
import root.form.MainForm;
import root.service.TuserService;
import root.service.MurmurService;


public class DeleteAction {

	@Resource
	protected UserDto userDto;

	@ActionForm
	@Resource
	protected MainForm mainForm;

	@Resource
    protected MurmurService murmurService;

	@Resource
	protected TuserService tuserService;

	// JdbcManagerのインスタンスを取得
	JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");

	//自分のつぶやきを削除する
		@Execute(validator=false,urlPattern = "{tubuyakiid}")
		public String index(){

			int userid = userDto.userID;

			int delete_murID = mainForm.tubuyakiid;
			System.out.println("もろきゅう"+delete_murID);


			Murmur delResult=new Murmur();
			delResult.murmurid=delete_murID;

			int count=jdbcManager.delete(delResult).execute();

	    	//投稿数を1減らす
	    	Tuser tuser =
	    			jdbcManager
	    			.from(Tuser.class)
	    			.where(eq(tuser().userid(),userid))
	    			.getSingleResult();
	    		tuser.postNum -=1;
	    		tuserService.update(tuser);

			return "/main";
		}


}
