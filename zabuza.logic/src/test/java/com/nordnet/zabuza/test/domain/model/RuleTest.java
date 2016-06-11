package com.nordnet.zabuza.test.domain.model;

import static com.nordnet.zabuza.domain.model.constants.Level.OK;
import static com.nordnet.common.valueObject.utils.JSon.canBeJSonSerialized;
import static com.nordnet.common.valueObject.utils.Utils.hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.test.GlobalTestCase;
import com.nordnet.common.valueObject.identifier.Identifier;

public class RuleTest extends GlobalTestCase {

	/** {@link ConnectionDataBase} CONNECTION_DATA_BASE. */
	private final static ConnectionDataBase CONNECTION_DATA_BASE = ConnectionDataBase.builder().host("host")
			.identifier(Identifier.build("identifier")).schema("schema").user("user").userPassword("userPassword")
			.build();

	/** {@link Query} QUERY. */
	private final static Query QUERY = Query.builder().identifier(Identifier.build("query"))
			.result(Arrays.asList("result")).sql("sql").build();

	/** successful test. */
	@Test
	public void successful() {
		Rule parent_rule =
				Rule.builder().dataBase(CONNECTION_DATA_BASE).descriptif("descriptif")
						.identifier(Identifier.build("identifier")).level(OK).query(QUERY).build();
		Rule rule =
				Rule.builder().dataBase(CONNECTION_DATA_BASE).descriptif("descriptif")
						.identifier(Identifier.build("identifier")).level(OK).parent(parent_rule).query(QUERY).build();
		assertTrue(hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo(Rule.class, rule));
		assertTrue(canBeJSonSerialized(rule));
	}

}
