package com.wobiancao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.wobiancao.entity.Merchant;

public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {

}
