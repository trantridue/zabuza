package com.nordnet.zabuza.test.domain.model;

import static com.nordnet.common.valueObject.utils.JSon.canBeJSonSerialized;
import static com.nordnet.common.valueObject.utils.Utils.hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.test.GlobalTestCase;
import com.nordnet.common.valueObject.identifier.Identifier;

public class ConnectionDataBaseTest extends GlobalTestCase {

	/** successful test. */
	@Test
	public void successful() {
		ConnectionDataBase connectionDataBase =
				ConnectionDataBase.builder().host("host").identifier(Identifier.build("identifier")).schema("schema")
						.user("user").userPassword("userPassword").build();
		assertTrue(hasProtectedEmptyConstructorEqualsAndHashCodeAndCompareTo(ConnectionDataBase.class,
				connectionDataBase));
		assertTrue(canBeJSonSerialized(connectionDataBase));
	}
}
