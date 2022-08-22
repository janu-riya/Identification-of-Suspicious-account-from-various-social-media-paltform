package com.manthan.ti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manthan.ti.entity.SentimentTable;

@Repository
public interface SentimentTableRepository extends JpaRepository<SentimentTable, Long> {

	@Query(value = "select id from sentiment where search_term_id = ?1",  nativeQuery = true)
	List<Long> findBySearchTermId(Long searchTermId);
}
