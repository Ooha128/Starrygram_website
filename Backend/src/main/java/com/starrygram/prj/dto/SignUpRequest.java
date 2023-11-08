package com.starrygram.prj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
	private String email;
	private String firstName;
	private String LastName;
	private String password;
	private String cnfPwd;
	private String phoneNumber;
}
