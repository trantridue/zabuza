package com.nordnet.zabuza.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nordnet.common.valueObject.utils.Null;
import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.domain.repository.ConnectionDataBaseRepository;
import com.nordnet.zabuza.domain.services.AddConnectionDataBaseService.AbstractAddConnectionDataBaseLauncher;

@Service("addConnectionDataBaseService")
public class AddConnectionDataBaseServiceImpl extends AbstractAddConnectionDataBaseLauncher {

	/** {@link ConnectionDataBaseRepository} connectionDataBaseRepository. */
	@Autowired
	private ConnectionDataBaseRepository connectionDataBaseRepository;

	@Override
	protected ConnectionDataBase addConnectionDataBase(final Builder builder) {
		Null.checkNotNull("builder", builder);

		ConnectionDataBase connectionDataBase =
				ConnectionDataBase.builder().host(builder.getHost()).identifier(builder.getIdentifier())
						.schema(builder.getSchema()).user(builder.getUser()).userPassword(builder.getUserPassword())
						.build();

		return connectionDataBaseRepository.save(connectionDataBase);
	}
}
