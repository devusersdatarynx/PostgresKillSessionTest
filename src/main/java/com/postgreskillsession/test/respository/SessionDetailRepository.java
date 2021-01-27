package com.postgreskillsession.test.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.postgreskillsession.test.entity.SessionDetails;

public interface SessionDetailRepository extends JpaRepository<SessionDetails, Long>{

	@Query(nativeQuery = true, value = "select pid as process_id, datname as database_name, application_name, state from pg_stat_activity;")
	List<SessionDetails> getSession();
}
