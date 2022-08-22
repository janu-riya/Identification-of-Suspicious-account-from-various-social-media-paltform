package com.manthan.ti.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manthan.ti.entity.SearchTerm;

@Repository
public interface SearchTermRepo extends JpaRepository<SearchTerm, Long> {

}