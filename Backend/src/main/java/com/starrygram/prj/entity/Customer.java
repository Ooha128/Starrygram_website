package com.starrygram.prj.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(length = 50)
	private String firstName;
	
	@Column(length = 50)
	private String lastName;
	
	@Column
	private String password;
	
	@Column(length = 10)
	private String phoneNumber;
	
	@OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Addresses> addresses;
	
	@OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders;
	
	@OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ShoppingCart> shoppingCart;
	
	@OneToOne(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Wishlist wishlist;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "role")
	private Role role;
}
