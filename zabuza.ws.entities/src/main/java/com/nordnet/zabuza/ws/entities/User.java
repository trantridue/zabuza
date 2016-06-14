package com.nordnet.zabuza.ws.entities;

import org.slf4j.LoggerFactory;
import org.joda.time.LocalDateTime;

/**
 * User.
 *
 * this class is auto generated by the com.nordnet.common project : entities-maven-plugin,
 * do not edit this class directly, all change will be lost on next build ...
 *
 * @author MMORTIER, JLDANSOU
 *
 */
public class User implements Comparable<User> {

	/** {@link String} username. */
	protected final String username;

	/** {@link String} password. */
	protected final String password;

	/** {@link String} name. */
	protected final String name;

	/** {@link String} email. */
	protected final String email;

	/** {@link String} address. */
	protected final String address;

	/** {@link LocalDateTime} createDate. */
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.nordnet.common.valueObject.utils.json.LocalDateTimeDeserializer.class)
	@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.nordnet.common.valueObject.utils.json.LocalDateTimeSerializer.class)
	protected final LocalDateTime createDate;

	/** {@link LocalDateTime} updateDate. */
	@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.nordnet.common.valueObject.utils.json.LocalDateTimeDeserializer.class)
	@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.nordnet.common.valueObject.utils.json.LocalDateTimeSerializer.class)
	protected final LocalDateTime updateDate;

	/**
	 * default deprecated Constructor.
	 *
	 * @deprecated
	 */
	@Deprecated
	protected User() {
		username = null;
		password = null;
		name = null;
		email = null;
		address = null;
		createDate = null;
		updateDate = null;
	}

	/**
	 * Constructor.
	 *
	 * @param builder the {@link Builder} to use.
	 */
	protected User(final Builder builder) {
		username = builder.username;
		password = builder.password;
		name = builder.name;
		email = builder.email;
		address = builder.address;
		createDate = builder.createDate;
		updateDate = builder.updateDate;
	
		// check parameters
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("username", username);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("password", password);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("name", name);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("email", email);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("address", address);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("createDate", createDate);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("updateDate", updateDate);
	}

	/**
	 * builder of {@link User}.
	 *
	 * @return new {@link Builder} for {@link User}
	 */
	public static Builder builder() {
		return new Builder();
	}
	/**
	 * Builder for {@link User}.
	 *
	 * @author MMORTIER, JLDANSOU
	 *
	 */
	public static class Builder {
		
		/** {@link String} username. */
		protected String username;

		/** {@link String} password. */
		protected String password;

		/** {@link String} name. */
		protected String name;

		/** {@link String} email. */
		protected String email;

		/** {@link String} address. */
		protected String address;

		/** {@link LocalDateTime} createDate. */
		@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.nordnet.common.valueObject.utils.json.LocalDateTimeDeserializer.class)
		@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.nordnet.common.valueObject.utils.json.LocalDateTimeSerializer.class)
		protected LocalDateTime createDate;

		/** {@link LocalDateTime} updateDate. */
		@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = com.nordnet.common.valueObject.utils.json.LocalDateTimeDeserializer.class)
		@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = com.nordnet.common.valueObject.utils.json.LocalDateTimeSerializer.class)
		protected LocalDateTime updateDate;

		/** default protected Constructor. */
		protected Builder() {
			super();
		}

		/**
		 * Setter of username.
		 *
		 * @param username
		 *			{@link String} the username to set.
		 * @return this {@link Builder}
		 */
		public Builder username(final String username) {
			this.username = username;
			return this;
		}

		/**
		 * Setter of password.
		 *
		 * @param password
		 *			{@link String} the password to set.
		 * @return this {@link Builder}
		 */
		public Builder password(final String password) {
			this.password = password;
			return this;
		}

		/**
		 * Setter of name.
		 *
		 * @param name
		 *			{@link String} the name to set.
		 * @return this {@link Builder}
		 */
		public Builder name(final String name) {
			this.name = name;
			return this;
		}

		/**
		 * Setter of email.
		 *
		 * @param email
		 *			{@link String} the email to set.
		 * @return this {@link Builder}
		 */
		public Builder email(final String email) {
			this.email = email;
			return this;
		}

		/**
		 * Setter of address.
		 *
		 * @param address
		 *			{@link String} the address to set.
		 * @return this {@link Builder}
		 */
		public Builder address(final String address) {
			this.address = address;
			return this;
		}

		/**
		 * Setter of createDate.
		 *
		 * @param createDate
		 *			{@link LocalDateTime} the createDate to set.
		 * @return this {@link Builder}
		 */
		public Builder createDate(final LocalDateTime createDate) {
			this.createDate = createDate;
			return this;
		}

		/**
		 * Setter of updateDate.
		 *
		 * @param updateDate
		 *			{@link LocalDateTime} the updateDate to set.
		 * @return this {@link Builder}
		 */
		public Builder updateDate(final LocalDateTime updateDate) {
			this.updateDate = updateDate;
			return this;
		}

		/**
		 * build new {@link User}.
		 *
		 * @return new {@link User} from this {@link Builder}
		 */
		public User build() {
			return new User(this);
		}

	}
	/**
	 * Getter of username.
	 *
	 * @return {@link String} the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Getter of password.
	 *
	 * @return {@link String} the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter of name.
	 *
	 * @return {@link String} the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter of email.
	 *
	 * @return {@link String} the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Getter of address.
	 *
	 * @return {@link String} the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Getter of createDate.
	 *
	 * @return {@link LocalDateTime} the createDate
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	/**
	 * Getter of updateDate.
	 *
	 * @return {@link LocalDateTime} the updateDate
	 */
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		User user = (User) obj;
		return com.nordnet.common.valueObject.utils.Equals.TRUE
			.andEquals(getUsername(), user.getUsername())
			.andEquals(getPassword(), user.getPassword())
			.andEquals(getName(), user.getName())
			.andEquals(getEmail(), user.getEmail())
			.andEquals(getAddress(), user.getAddress())
			.andEquals(getCreateDate(), user.getCreateDate())
			.andEquals(getUpdateDate(), user.getUpdateDate()).isEquals();
	}

	@Override
	public int hashCode() {
	return new com.nordnet.common.valueObject.utils.HashCode(null)
		.append(getUsername())
		.append(getPassword())
		.append(getName())
		.append(getEmail())
		.append(getAddress())
		.append(getCreateDate())
		.append(getUpdateDate()).hashCode();
	}

	@Override
	public String toString() {
		try {
			return com.nordnet.common.valueObject.utils.JSon.toPrettyJson(this);
		} catch (com.fasterxml.jackson.core.JsonProcessingException jpe) {
			LoggerFactory.getLogger(User.class).error("error with toString", jpe);
			return super.toString();
		}
	}

	@Override
	public int compareTo(final User o) {
		return new com.nordnet.common.valueObject.utils.Compare(true, true)
		.andCompare(getUsername(), o.getUsername())
		.andCompare(getPassword(), o.getPassword())
		.andCompare(getName(), o.getName())
		.andCompare(getEmail(), o.getEmail())
		.andCompare(getAddress(), o.getAddress())
		.andCompare(getCreateDate(), o.getCreateDate())
		.andCompare(getUpdateDate(), o.getUpdateDate()).compare();
	}


}
