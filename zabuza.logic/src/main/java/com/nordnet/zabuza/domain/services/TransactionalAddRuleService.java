package com.nordnet.zabuza.domain.services;

import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.domain.services.AddRuleService.AbstractAddRuleLauncher.Builder;

public interface TransactionalAddRuleService {

	public Rule addRule(final Builder builder);
}
