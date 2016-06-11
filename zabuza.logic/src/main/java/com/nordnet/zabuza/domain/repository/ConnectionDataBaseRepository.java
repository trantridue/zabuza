package com.nordnet.zabuza.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.domain.model.ConnectionDataBase;

public interface ConnectionDataBaseRepository extends JpaRepository<ConnectionDataBase, Long> {

	public ConnectionDataBase findByIdentifier(Identifier identifier);
}
