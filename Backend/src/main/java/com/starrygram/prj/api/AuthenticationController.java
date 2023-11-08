package com.starrygram.prj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starrygram.prj.dto.JwtAuthenticationResponse;
import com.starrygram.prj.dto.SignInRequest;
import com.starrygram.prj.dto.SignUpRequest;
import com.starrygram.prj.services.AuthenticationService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:3000/")
@Validated
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request){
		return ResponseEntity.ok(authenticationService.signup(request));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request){
		return ResponseEntity.ok(authenticationService.signin(request));
	}
}
