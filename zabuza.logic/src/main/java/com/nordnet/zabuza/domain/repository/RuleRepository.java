package com.nordnet.zabuza.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.domain.model.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long> {

	public Rule findByIdentifier(Identifier identifier);
}
