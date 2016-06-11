package com.nordnet.zabuza.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nordnet.common.valueObject.utils.Null;
import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.zabuza.domain.repository.QueryRepository;
import com.nordnet.zabuza.domain.services.AddQueryService.AbstractAddQueryLauncher;

@Service("addQueryService")
public class AddQueryServiceImpl extends AbstractAddQueryLauncher {

	/** {@link QueryRepository} queryRepository. */
	@Autowired
	private QueryRepository queryRepository;

	@Override
	protected Query addQuery(final Builder builder) {
		Null.checkNotNull("builder", builder);

		Query query =
				Query.builder().identifier(builder.getIdentifier()).result(builder.getResult()).sql(builder.getSql())
						.build();

		return queryRepository.save(query);
	}

}
