package root.action.main;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import root.form.MainForm;

public class ReplyanAction {

	@ActionForm
	@Resource
	protected MainForm mainForm;


	@Execute(validator = false, urlPattern="{}")
	public String index() {

	    return null;
	}
}
