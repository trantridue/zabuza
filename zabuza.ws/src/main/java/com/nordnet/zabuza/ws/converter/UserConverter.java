/**
 *
 */
package com.nordnet.zabuza.ws.converter;

import org.dozer.CustomConverter;

import com.nordnet.common.valueObject.exception.InvalidArgumentException;
import com.nordnet.zabuza.domain.model.User;

/**
 * The Class User.
 *
 * @author jldansou
 */
public class UserConverter implements CustomConverter {

	@Override
	public Object convert(final Object destination, final Object source, final Class<?> destinationClass,
			final Class<?> sourceClass) {

		if (source instanceof User) {
			User user = (User) source;

			return com.nordnet.zabuza.ws.entities.User.builder().username(user.getUsername())
					.address(user.getAddress()).password(user.getPassword()).email(user.getEmail())
					.name(user.getName()).createDate(user.getCreateDate()).updateDate(user.getUpdateDate()).build();
		}
		throw new InvalidArgumentException(source.getClass().getName(), "bad source for conversion");
	}
}
