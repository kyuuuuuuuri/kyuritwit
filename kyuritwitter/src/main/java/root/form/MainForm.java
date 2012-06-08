package root.form;

import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Required;
import org.seasar.struts.annotation.Msg;

public class MainForm {

	@Required(msg=@Msg(key="0字以上で入力してください",resource=false))
	@Maxlength(maxlength = 140
			, msg =@Msg(key="140字で入力してください",resource=false))
	public String tubuyaki;

	@IntegerType
	public String page;

	public int userid;
	public int userid1;

	public int tubuyakiid;

	public Integer delete_id;

	public int retwit_tubuyaki;

	public String userni;

	public String rep_user;
}
