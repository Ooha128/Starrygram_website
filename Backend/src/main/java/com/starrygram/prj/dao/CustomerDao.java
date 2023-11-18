package com.starrygram.prj.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starrygram.prj.entity.Customer;


public interface CustomerDao extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);
	Customer findFirstByEmail(String email);
}
