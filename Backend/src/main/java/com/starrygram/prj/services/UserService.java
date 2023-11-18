package com.starrygram.prj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.starrygram.prj.dao.CustomerDao;
import com.starrygram.prj.entity.Customer;

@Service
public class UserService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<Customer> getCustomers() {
		return customerDao.findAll();
	}
	
	public Customer addCustomer(Customer c) {
		return customerDao.save(c);
	}
	
	public Customer getCustomerById(Long id) {
		return customerDao.findById(id).get();
	}
	
	public Customer getCustomerByEmail(String email) {
		return customerDao.findFirstByEmail(email);
	}
	
	public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return (UserDetails) customerDao.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
