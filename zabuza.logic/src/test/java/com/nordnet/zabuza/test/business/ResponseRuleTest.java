package com.nordnet.zabuza.test.business;

import static com.nordnet.common.valueObject.utils.Utils.hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo;
import static com.nordnet.zabuza.domain.model.constants.Level.OK;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.business.ResponseRule;
import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.test.GlobalTestCase;

public class ResponseRuleTest extends GlobalTestCase {

	/** {@link ConnectionDataBase} CONNECTION_DATA_BASE. */
	private final static ConnectionDataBase CONNECTION_DATA_BASE = ConnectionDataBase.builder().host("host")
			.identifier(Identifier.build("identifier")).schema("schema").user("user").userPassword("userPassword")
			.build();

	/** {@link Query} QUERY. */
	private final static Query QUERY = Query.builder().identifier(Identifier.build("query"))
			.result(Arrays.asList("result")).sql("sql").build();

	/** {@link Rule} PARENT_RULE. */
	private final static Rule PARENT_RULE = Rule.builder().dataBase(CONNECTION_DATA_BASE).descriptif("descriptif")
			.identifier(Identifier.build("identifier")).level(OK).query(QUERY).build();

	/** {@link Rule} RULE. */
	private final static Rule RULE = Rule.builder().dataBase(CONNECTION_DATA_BASE).descriptif("descriptif")
			.identifier(Identifier.build("identifier")).level(OK).parent(PARENT_RULE).query(QUERY).build();

	@SuppressWarnings("serial")
	private final static Map<String, String> MAP = new HashMap<String, String>() {

		{
			put("key", "value");
		}
	};

	private final static List<Map<String, String>> LIST = Arrays.asList(MAP);

	/** successful test. */
	@Test
	public void successful() {
		ResponseRule parent_responseRule =
				ResponseRule.builder().results(LIST).rule(RULE).unmatchedRuleDetail(LIST).build();
		ResponseRule responseRule =
				ResponseRule.builder().parentResponse(parent_responseRule).results(LIST).rule(RULE)
						.unmatchedRuleDetail(LIST).build();
		assertTrue(hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo(ResponseRule.class, responseRule));
	}

}
