package com.nordnet.zabuza.test.domain.services;

import static com.nordnet.zabuza.domain.model.constants.Level.OK;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.domain.services.AddConnectionDataBaseService;
import com.nordnet.zabuza.domain.services.AddQueryService;
import com.nordnet.zabuza.domain.services.AddRuleService;
import com.nordnet.zabuza.test.GlobalTestCase;
import com.nordnet.common.valueObject.identifier.Identifier;

public class AddRuleServiceTest extends GlobalTestCase {

	/** {@link AddConnectionDataBaseService} addConnectionDataBaseService. */
	@Autowired
	private AddConnectionDataBaseService addConnectionDataBaseService;

	/** {@link AddQueryService} addQueryService. */
	@Autowired
	private AddQueryService addQueryService;

	/** {@link AddRuleService} addRuleService. */
	@Autowired
	private AddRuleService addRuleService;

	/** {@link ConnectionDataBase} CONNECTION_DATA_BASE. */
	private final static ConnectionDataBase CONNECTION_DATA_BASE = ConnectionDataBase.builder().host("host")
			.identifier(Identifier.build("connectionDataBase")).schema("schema").user("user")
			.userPassword("userPassword").build();

	/** {@link Query} QUERY. */
	private final static Query QUERY = Query.builder().identifier(Identifier.build("query"))
			.result(Arrays.asList("result")).sql("sql").build();

	/** {@link Rule} RULE. */
	private final static Rule RULE = Rule.builder().dataBase(CONNECTION_DATA_BASE).descriptif("descriptif")
			.identifier(Identifier.build("rule")).level(OK).query(QUERY).build();

	@Test
	@DatabaseSetup(value = "/dataset/emptyDb.xml")
	// @Transactional
	public void successfulTest() {
		// add connectionDataBase
		ConnectionDataBase connectionDataBase =
				addConnectionDataBaseService.launcher().host("host").identifier(Identifier.build("connectionDataBase"))
						.schema("schema").user("user").userPassword("userPassword").execute();
		assertEquals(CONNECTION_DATA_BASE, connectionDataBase);

		// add query
		Query query =
				addQueryService.launcher().identifier(Identifier.build("query")).result(Arrays.asList("result"))
						.sql("sql").execute();
		assertEquals(QUERY, query);

		// add rule
		Rule rule =
				addRuleService.launcher().dataBase(Identifier.build("connectionDataBase")).descriptif("descriptif")
						.identifier(Identifier.build("rule")).level(OK).query(Identifier.build("query")).execute();
		assertEquals(RULE, rule);

		// add child rule
		rule =
				addRuleService.launcher().dataBase(Identifier.build("connectionDataBase")).descriptif("descriptif")
						.identifier(Identifier.build("rule2")).level(OK).parent(Identifier.build("rule"))
						.query(Identifier.build("query")).execute();
		assertEquals(RULE, rule.getParent());
	}

}
