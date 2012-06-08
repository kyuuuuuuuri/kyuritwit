package root.form;

import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Required;

public class LoginForm {

	@Required
	@Minlength(minlength = 4)
	@Maxlength(maxlength = 20)
	@Mask(mask = "^[-a-zA-Z0-9_]+$")
	public String UserName;

	@Required
	@Minlength(minlength = 4)
	@Maxlength(maxlength = 8)
	@Mask(mask = "^[a-zA-Z0-9]+$")
	public String Pass;


}
