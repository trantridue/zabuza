package com.nordnet.zabuza.ws.entities.test;

import static com.nordnet.common.valueObject.utils.JSon.canBeJSonSerialized;
import static com.nordnet.common.valueObject.utils.Utils.hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.nordnet.common.valueObject.identifier.Identifier;
import java.util.Arrays;
import java.util.List;
import static java.util.Arrays.asList;
import com.nordnet.zabuza.ws.entities.Query;

/**
 * generic test for {@link Query} entity.
 *
 * this class is auto generated by the com.nordnet.common project : entities-maven-plugin,
 * do not edit this class directly, all change will be lost on next build ...
 */
@SuppressWarnings("unused")
public class QueryTest extends GlobalTestCase {

	/**
	 * test  for Query  methods.
	 * <li> constructors,
	 * <li> {@link Query#equals(Object)},
	 * <li> {@link Query#hashCode()},
	 * <li> {@link Query#toString()}
	 * <li> {@link Query#compareTo(Query)}
	 */
	@Test
	public void successTest() {
		Query tmp = Query.builder()
			.identifier(Identifier.build("identifier"))
			.sql("String")
			.result(asList("string")).build();
		assertTrue(hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo(Query.class, tmp));
		assertTrue(canBeJSonSerialized(tmp));
	}
}
