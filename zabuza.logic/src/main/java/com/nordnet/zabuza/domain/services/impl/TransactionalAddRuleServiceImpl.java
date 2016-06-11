package com.nordnet.zabuza.domain.services.impl;

import static com.nordnet.common.valueObject.utils.Null.checkExist;
import static com.nordnet.common.valueObject.utils.Null.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.domain.repository.ConnectionDataBaseRepository;
import com.nordnet.zabuza.domain.repository.QueryRepository;
import com.nordnet.zabuza.domain.repository.RuleRepository;
import com.nordnet.zabuza.domain.services.AddRuleService.AbstractAddRuleLauncher.Builder;

@Service("transactionAddddRuleService")
public class TransactionalAddRuleServiceImpl {

	/** {@link RuleRepository} ruleREpository. */
	@Autowired
	private RuleRepository ruleREpository;

	/** {@link ConnectionDataBaseRepository} connectionDataBaseRepository. */
	@Autowired
	private ConnectionDataBaseRepository connectionDataBaseRepository;

	/** {@link QueryRepository} queryRepository. */
	@Autowired
	private QueryRepository queryRepository;

	@Transactional
	public Rule addRule(final Builder builder) {
		checkNotNull("builder", builder);

		// get the data base
		ConnectionDataBase connectionDataBase = connectionDataBaseRepository.findByIdentifier(builder.getDataBase());
		checkExist("connectionDataBase", connectionDataBase, builder.getDataBase());

		// get the query
		Query query = queryRepository.findByIdentifier(builder.getQuery());
		checkExist("query", query, builder.getQuery());

		// get the parent
		Rule parent = ruleREpository.findByIdentifier(builder.getParent());

		Rule rule =
				Rule.builder().dataBase(connectionDataBase).descriptif(builder.getDescriptif())
						.identifier(builder.getIdentifier()).level(builder.getLevel()).parent(parent).query(query)
						.build();

		return ruleREpository.save(rule);
	}
}
