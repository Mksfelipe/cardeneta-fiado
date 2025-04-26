package br.com.mercadinho.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadinho.domain.payload.request.LoginRequest;
import br.com.mercadinho.domain.payload.request.SignupRequest;
import br.com.mercadinho.domain.payload.response.AuthTokenModel;
import br.com.mercadinho.domain.payload.response.MessageResponse;
import br.com.mercadinho.domain.service.UserService;
import br.com.mercadinho.security.TokenProvider;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<AuthTokenModel> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getCpf(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new AuthTokenModel(token));
	}

	@PostMapping("/signup")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
		userService.registerUser(signUpRequest);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	/**
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}
	*/
}
