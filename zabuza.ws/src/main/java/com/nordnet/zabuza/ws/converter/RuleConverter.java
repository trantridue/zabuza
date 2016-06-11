/**
 *
 */
package com.nordnet.zabuza.ws.converter;

import org.dozer.CustomConverter;

import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.ws.entities.constants.Level;
import com.nordnet.common.valueObject.exception.InvalidArgumentException;

/**
 * The Class AppendixItemConverter.
 *
 * @author jldansou
 */
public class RuleConverter implements CustomConverter {

	@Override
	public Object convert(final Object destination, final Object source, final Class<?> destinationClass,
			final Class<?> sourceClass) {

		if (source instanceof Rule) {
			Rule rule = (Rule) source;

			return com.nordnet.zabuza.ws.entities.Rule.builder()
					.connectionDataBase(rule.getDataBase().getIdentifier()).descriptif(rule.getDescriptif())
					.identifier(rule.getIdentifier()).level(Level.valueOf(rule.getLevel().name()))
					.parent(rule.getParent() != null ? rule.getParent().getIdentifier() : null)
					.query(rule.getQuery().getIdentifier()).build();
		}
		throw new InvalidArgumentException(source.getClass().getName(), "bad source for conversion");
	}
}
