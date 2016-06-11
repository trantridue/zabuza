package com.nordnet.zabuza.test.domain.model;

import static com.nordnet.common.valueObject.utils.JSon.canBeJSonSerialized;
import static com.nordnet.common.valueObject.utils.Utils.hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.zabuza.test.GlobalTestCase;
import com.nordnet.common.valueObject.identifier.Identifier;

public class QueryTest extends GlobalTestCase {

	/** successful test. */
	@Test
	public void successful() {
		Query query =
				Query.builder().identifier(Identifier.build("query")).result(Arrays.asList("result")).sql("sql")
						.build();
		assertTrue(hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo(Query.class, query));
		assertTrue(canBeJSonSerialized(query));
	}

}
