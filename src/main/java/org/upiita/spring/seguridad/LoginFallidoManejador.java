package org.upiita.spring.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginFallidoManejador extends SimpleUrlAuthenticationFailureHandler {


	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		Integer loginFallido= (Integer)request.getSession().getAttribute("LogiinsFallidos");
	
				//Si es la primera vez que se equivoco
				if(loginFallido==null){
					loginFallido =1;
				}
				else{
					//se incrementa el contador de logins fallidos
					loginFallido++;
				}
		
		super.onAuthenticationFailure(request, response, exception);
		
		
		
	}
	}
	
		
		
}
