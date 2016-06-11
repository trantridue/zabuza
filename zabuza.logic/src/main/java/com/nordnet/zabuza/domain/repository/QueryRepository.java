package com.nordnet.zabuza.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.domain.model.Query;

public interface QueryRepository extends JpaRepository<Query, Long> {

	public Query findByIdentifier(Identifier identifier);
}
