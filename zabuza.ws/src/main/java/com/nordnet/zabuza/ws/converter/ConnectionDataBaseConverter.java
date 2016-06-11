/**
 *
 */
package com.nordnet.zabuza.ws.converter;

import org.dozer.CustomConverter;

import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.common.valueObject.exception.InvalidArgumentException;

/**
 * The Class AppendixItemConverter.
 *
 * @author jldansou
 */
public class ConnectionDataBaseConverter implements CustomConverter {

	@Override
	public Object convert(final Object destination, final Object source, final Class<?> destinationClass,
			final Class<?> sourceClass) {

		if (source instanceof ConnectionDataBase) {
			ConnectionDataBase connectionDataBase = (ConnectionDataBase) source;

			return com.nordnet.zabuza.ws.entities.ConnectionDataBase.builder()
					.identifier(connectionDataBase.getIdentifier()).host(connectionDataBase.getHost())
					.schema(connectionDataBase.getSchema()).user(connectionDataBase.getUser())
					.userPassword(connectionDataBase.getUserPassword()).build();
		}
		throw new InvalidArgumentException(source.getClass().getName(), "bad source for conversion");
	}
}
