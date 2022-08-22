package com.manthan.ti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manthan.ti.entity.Token;



@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	@Query(value = "select id from token where search_term_id = ?1",  nativeQuery = true)
	List<Long> findBySearchTermId(Long searchTermId);
}
