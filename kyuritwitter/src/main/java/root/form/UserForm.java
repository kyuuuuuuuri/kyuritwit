package root.form;

import org.seasar.struts.annotation.EmailType;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Required;

public class UserForm {


	@Required
	@Minlength(minlength = 4)
	@Maxlength(maxlength = 20)
	@Mask(mask = "^[-a-zA-Z0-9_ぁ-んァ-ヶ亜-黑ａ-ｚＡ-Ｚ０-９！”＃＄％＆’（）＊＋－．，／：；＜＝＞？＠￥＾＿～｛｜｝「」]+$")
	public String userName;

	@Required
	@Minlength(minlength = 4)
	@Maxlength(maxlength = 20)
	@Mask(mask = "^[-a-zA-Z0-9_]+$")
	public String userNick;

	@Required
	@Minlength(minlength = 4)
	@Maxlength(maxlength = 8)
	@Mask(mask = "^[a-zA-Z0-9]+$")
	public String pass;

	@Required
	@Mask(mask = "^[a-zA-Z0-9]+$")
	@Minlength(minlength = 4)
	@Maxlength(maxlength = 8)
	public String checkPass;

	@Required
	@EmailType
	@Maxlength(maxlength = 100)
	public String mailAd;


}
