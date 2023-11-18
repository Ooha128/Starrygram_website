package com.starrygram.prj.services;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starrygram.prj.dao.CustomerDao;
import com.starrygram.prj.dto.JwtAuthenticationResponse;
import com.starrygram.prj.dto.SignInRequest;
import com.starrygram.prj.dto.SignUpRequest;
import com.starrygram.prj.entity.Customer;
import com.starrygram.prj.entity.Role;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private JwtService jwtService;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private CustomerDao customerDao;
	
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		var customer = Customer.builder().email(request.getEmail()).firstName(request.getFirstName()).lastName(request.getLastName()).password(request.getPassword()).phoneNumber(request.getPhoneNumber()).addresses(null).orders(null).shoppingCart(null).wishlist(null).role(Role.USER).build();
		customerDao.save(customer);
		String jwt = jwtService.generateToken(customer);
		return JwtAuthenticationResponse.builder().token(jwt).customer(customer).build();	
	}
	
	public JwtAuthenticationResponse signin(SignInRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var customer = customerDao.findByEmail(request.getEmail()).orElseThrow(()-> new IllegalArgumentException("Inavalid Email or Password"));
		String jwt = jwtService.generateToken(customer);
		return JwtAuthenticationResponse.builder().token(jwt).customer(customer).build();
	}
	
	@Transactional
	public ResponseEntity<Object> updatePassword(String email, String newPassword){
		Map<String, Object> body=new LinkedHashMap<>();
		Customer customer = customerDao.findFirstByEmail(email);
		if(customer==null)
		{
			body.put("status",HttpStatus.FORBIDDEN);
			body.put("timestamp",new Date());
			body.put("message","Invalid Email Address");
			return new ResponseEntity<Object>(body,HttpStatus.FORBIDDEN);
		}
		else {
			customer.setPassword(passwordEncoder.encode(newPassword));
			body.put("status",HttpStatus.OK);
			body.put("timestamp",new Date());
			body.put("message","Password Updated Successfully");
			body.put("customer",customer);
			return new ResponseEntity<Object>(body,HttpStatus.OK);
		}
	}
}
