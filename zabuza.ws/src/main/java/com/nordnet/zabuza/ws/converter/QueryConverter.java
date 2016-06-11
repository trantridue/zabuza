/**
 *
 */
package com.nordnet.zabuza.ws.converter;

import org.dozer.CustomConverter;

import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.common.valueObject.exception.InvalidArgumentException;

/**
 * The Class AppendixItemConverter.
 *
 * @author jldansou
 */
public class QueryConverter implements CustomConverter {

	@Override
	public Object convert(final Object destination, final Object source, final Class<?> destinationClass,
			final Class<?> sourceClass) {

		if (source instanceof Query) {
			Query query = (Query) source;

			return com.nordnet.zabuza.ws.entities.Query.builder().identifier(query.getIdentifier())
					.result(query.getResult()).sql(query.getSql()).build();
		}
		throw new InvalidArgumentException(source.getClass().getName(), "bad source for conversion");
	}
}
