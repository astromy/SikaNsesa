package config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class RESTAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	  @Override
	  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	      AuthenticationException exception) throws IOException, ServletException {
	    
	    super.onAuthenticationFailure(request, response, exception);
	  }
	}