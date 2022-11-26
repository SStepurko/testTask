package com.example.testTask.config;

import com.example.testTask.security.JwtImpl;
import com.example.testTask.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for security controller for HTTP chain.
 * Extract data from JWT token in requests. Check and set security context
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

	//	Dependency Injection
	private final JwtImpl jwtImp;
	private final UserService userDetailService;

	public JwtFilter(JwtImpl jwtImp, UserService userDetailService) {
		this.jwtImp = jwtImp;
		this.userDetailService = userDetailService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;

//		check if header has correct auth with "Bearer_" and extract username from it
		if (authHeader != null && authHeader.startsWith("Bearer_")) {
			jwt = authHeader.substring(7);
			username = jwtImp.extractUsername(jwt);
		}

//		check if JWT token is valid for username in request and make it authorized
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
//			set security context for this request
			if (jwtImp.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);

	}
}
