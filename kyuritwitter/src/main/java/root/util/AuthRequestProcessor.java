package root.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.action.S2RequestProcessor;
import org.seasar.struts.config.S2ExecuteConfig;
import org.seasar.struts.util.S2ExecuteConfigUtil;

import root.annotation.Auth;
import root.dto.UserDto;

public class AuthRequestProcessor extends S2RequestProcessor {
	@Override
	protected boolean processRoles(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
	throws IOException, ServletException{

		S2ExecuteConfig executeConfig
		=S2ExecuteConfigUtil.getExecuteConfig();

		//アノテーションの取得
		Auth auth = executeConfig.getMethod().getAnnotation(Auth.class);

		if(auth != null){
			//UserDtoを取得
			UserDto userDto = SingletonS2Container.getComponent(UserDto.class);

			//UserDtoのisLogin()メソッドの戻り値をチェック
			boolean isLogin = userDto.isLogin();

			//Loginしてなかったら例外
			if(!isLogin){
				throw new RuntimeException("ログインしていません。");
			}
		}
		return super.processRoles(request, response, mapping);

	}

}
