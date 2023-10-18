package com.starrygram.prj.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Order order;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfPayment;
	
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
}
