package fa.training.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();;
		HttpSession session = servletRequest.getSession();
		String token = (String) session.getAttribute("token");
		request.getHeaders().add("Authorization", token);
		return execution.execute(request, body);

	}
	

}
