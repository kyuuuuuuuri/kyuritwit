package root.action.main;

import javax.annotation.Resource;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static root.entity.Names.*;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.dto.UserDto;
import root.form.MainForm;
import root.service.MurmurService;
import root.entity.Murmur;
import root.entity.Tuser;
import root.service.TuserService;


public class RetwitAction {

	@ActionForm
	@Resource
	protected MainForm mainForm;

	@Resource
	protected UserDto userDto;

	@Resource
    protected MurmurService murmurService;

	@Resource
	protected TuserService tuserService;

	// JdbcManagerのインスタンスを取得
		JdbcManager jdbcManager=SingletonS2Container.getComponent("jdbcManager");

	//リツイートメソッド
		@Execute(validator = false,urlPattern="{tubuyakiid}")
		public String index(){
			//ログインユーザ
			int userid=userDto.userID;

			//Formに渡されたつぶやきを格納する変数
			int remurid = mainForm.tubuyakiid;
			Murmur remur;
			String retwit;

			Murmur murmur = new Murmur();

			//retwittubuyakiIDを元に、つぶやきを検索する
			remur=
				jdbcManager
				.from(Murmur.class)
				.where(eq(murmur().murmurid(),remurid))
				.getSingleResult();

			retwit=remur.murmur;

			//リツイート元ユーザ名＋リツイートつぶやき
			//remur=re_user+remur;

			//新しいつぶやきをデータベースに格納する
			murmur.userid = userid;
			murmur.murmur=retwit;
			murmur.dateTime=null;
			murmurService.insert(murmur);

			//自分の最新のつぶやきを更新する
			Tuser tuser =
				jdbcManager
				.from(Tuser.class)
				.where(eq(tuser().userid(), userid))
				.getSingleResult();
			tuser.newMur = retwit;
			tuser.postNum +=1;
			tuser.newMurD =null;
			tuserService.update(tuser);

			mainForm.tubuyakiid=0;
			return "/main";
		}
}
