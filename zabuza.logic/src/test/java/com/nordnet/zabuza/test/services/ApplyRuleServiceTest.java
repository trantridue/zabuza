package com.nordnet.zabuza.test.services;

import static com.nordnet.zabuza.domain.model.constants.Level.CRITICAL;
import static com.nordnet.zabuza.domain.model.constants.Level.OK;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nordnet.zabuza.business.ResponseRule;
import com.nordnet.zabuza.services.ApplyRuleService;
import com.nordnet.zabuza.test.GlobalTestCase;
import com.nordnet.common.valueObject.identifier.Identifier;

public class ApplyRuleServiceTest extends GlobalTestCase {

	@Autowired
	private ApplyRuleService applyRuleService;

	/** {@link Identifier} RULE_CODE. */
	private static Identifier RULE_CHECK_CONNECTION = Identifier.build("check_connection");
	private static Identifier RULE_CHECK_QUERY = Identifier.build("check_query");

	@Test
	@DatabaseSetup(value = "/dataset/initDb.xml")
	public void successfulTest() {
		ResponseRule response = applyRuleService.forRuleCode(RULE_CHECK_CONNECTION);
		Assert.assertEquals("check_connection;0;check all connection are used|nbResult=1 total=1", response.toNagios());
		Assert.assertEquals(OK, response.getLevel());

		response = applyRuleService.forRuleCode(RULE_CHECK_QUERY);
		Assert.assertEquals("check_query;2;check all query are used|nbResult=4 total=5\n[{id_query=5}]",
				response.toNagios());
		Assert.assertEquals(CRITICAL, response.getLevel());
	}

}
