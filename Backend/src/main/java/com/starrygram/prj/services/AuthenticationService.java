package com.starrygram.prj.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starrygram.prj.dao.CustomerDao;
import com.starrygram.prj.dto.JwtAuthenticationResponse;
import com.starrygram.prj.dto.SignInRequest;
import com.starrygram.prj.dto.SignUpRequest;
import com.starrygram.prj.entity.Customer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private JwtService jwtService;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private CustomerDao customerDao;
	
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		Customer customer = Customer.builder().email(request.getEmail());
	}
	
	public JwtAuthenticationResponse signin(SignInRequest request) {
	}
}
