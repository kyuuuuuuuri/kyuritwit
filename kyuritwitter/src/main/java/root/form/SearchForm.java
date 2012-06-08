package root.form;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.IntegerType;

@Component(instance=InstanceType.SESSION)
public class SearchForm implements Serializable{

	private static final long serialVersionUID = 1L;


	public String search;

	@IntegerType
	public String page;

	public Integer userid;

}
