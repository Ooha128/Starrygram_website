package com.starrygram.prj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starrygram.prj.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
