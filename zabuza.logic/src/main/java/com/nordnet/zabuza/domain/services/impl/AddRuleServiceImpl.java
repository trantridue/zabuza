package com.nordnet.zabuza.domain.services.impl;

import static com.nordnet.common.valueObject.utils.Null.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.domain.repository.RuleRepository;
import com.nordnet.zabuza.domain.services.AddRuleService.AbstractAddRuleLauncher;

@Service("addRuleService")
public class AddRuleServiceImpl extends AbstractAddRuleLauncher {

	/** {@link RuleRepository} ruleREpository. */
	@Autowired
	private TransactionalAddRuleServiceImpl transactionalAddRullService;

	@Override
	protected Rule addRule(final Builder builder) {
		checkNotNull("builder", builder);

		// as the method is protected and not the first call from the bean, the transactional annotation does not work.
		// so create new service with transaction.
		return transactionalAddRullService.addRule(builder);
	}
}
