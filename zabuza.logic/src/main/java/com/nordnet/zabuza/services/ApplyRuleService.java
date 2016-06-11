package com.nordnet.zabuza.services;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.business.ResponseRule;

public interface ApplyRuleService {

	public ResponseRule forRuleCode(Identifier ruleCode);
}
