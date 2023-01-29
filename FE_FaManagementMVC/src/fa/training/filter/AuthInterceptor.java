package fa.training.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String action = request.getServletPath();
		HttpSession session = request.getSession();
		if(action.startsWith("/static")||action.startsWith("/images")||action.startsWith("/error"))
		{
			return true;
		}
		if(!action.equals("/login")&& (session.getAttribute("token")==null || session.getAttribute("token").equals("")) )
		{
			response.sendRedirect(request.getContextPath()+"/login");
			return false;
		}
		return true;
	}
	
}
