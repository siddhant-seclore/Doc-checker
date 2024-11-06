package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.training.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	@Query("select c from Customer c JOIN c.documentList d where d.verified=false")
public List<Customer> findToBeVerified();
}
