package com.wobiancao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.wobiancao.entity.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	Customer findOneByOpenId(String openId);

}
