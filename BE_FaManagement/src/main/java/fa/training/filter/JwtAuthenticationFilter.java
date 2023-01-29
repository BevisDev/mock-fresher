package fa.training.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailsService;
	private JwtProvider jwtProvider;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService
			,JwtProvider jwtProvider) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
		this.jwtProvider = jwtProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// Get token from request header
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
			String token = authorizationHeader.replace("Bearer ", "");
			if (jwtProvider.validateToken(token)) {
				// decode token to get username
				String username = jwtProvider.getUserNameFromJwtToken(token);
				// get user details by username
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				// set user details to security context
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}
}
