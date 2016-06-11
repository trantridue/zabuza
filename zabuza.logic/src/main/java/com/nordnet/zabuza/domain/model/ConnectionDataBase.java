package com.nordnet.zabuza.domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.LoggerFactory;

import com.nordnet.common.valueObject.identifier.Identifier;

/**
 * DataBase.
 *
 * this class is auto generated by the com.nordnet.common project : entities-maven-plugin, do not edit this class
 * directly, all change will be lost on next build ...
 *
 * @author MMORTIER, Equipe OTS
 *
 */
@Entity
@Table(name = "connection_data_base")
public class ConnectionDataBase implements Comparable<ConnectionDataBase> {

	/** identifier. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", columnDefinition = "int(11)", nullable = false)
	protected Long id;

	/** {@link String} identifier. */
	protected final Identifier identifier;

	/** {@link String} host. */
	protected final String host;

	/** {@link String} schema. */
	@Column(name = "schema_name")
	protected final String schema;

	/** {@link String} user. */
	@Column(name = "user_name")
	protected final String user;

	/** {@link String} password. */
	@Column(name = "user_password")
	protected final String userPassword;

	/**
	 * default deprecated Constructor.
	 *
	 * @deprecated
	 */
	@Deprecated
	protected ConnectionDataBase() {
		identifier = null;
		host = null;
		schema = null;
		user = null;
		userPassword = null;
	}

	/**
	 * Constructor.
	 *
	 * @param builder
	 *            the {@link Builder} to use.
	 */
	protected ConnectionDataBase(final Builder builder) {
		identifier = builder.identifier;
		host = builder.host;
		schema = builder.schema;
		user = builder.user;
		userPassword = builder.userPassword;

		// check parameters
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("identifier", identifier);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("host", host);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("schema", schema);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("user", user);
		com.nordnet.common.valueObject.utils.Null.checkNotNullOrEmpty("userPassword", userPassword);
	}

	/**
	 * builder of {@link ConnectionDataBase}.
	 *
	 * @return new {@link Builder} for {@link ConnectionDataBase}
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder for {@link ConnectionDataBase}.
	 *
	 * @author MMORTIER, Equipe OTS
	 *
	 */
	public static class Builder {

		/** {@link String} identifier. */
		protected Identifier identifier;

		/** {@link String} host. */
		protected String host;

		/** {@link String} schema. */
		protected String schema;

		/** {@link String} user. */
		protected String user;

		/** {@link String} password. */
		protected String userPassword;

		/** default protected Constructor. */
		protected Builder() {
			super();
		}

		/**
		 * Setter of identifier.
		 *
		 * @param identifier
		 *            {@link String} the identifier to set.
		 * @return this {@link Builder}
		 */
		public Builder identifier(final Identifier identifier) {
			this.identifier = identifier;
			return this;
		}

		/**
		 * Setter of host.
		 *
		 * @param host
		 *            {@link String} the host to set.
		 * @return this {@link Builder}
		 */
		public Builder host(final String host) {
			this.host = host;
			return this;
		}

		/**
		 * Setter of schema.
		 *
		 * @param schema
		 *            {@link String} the schema to set.
		 * @return this {@link Builder}
		 */
		public Builder schema(final String schema) {
			this.schema = schema;
			return this;
		}

		/**
		 * Setter of user.
		 *
		 * @param user
		 *            {@link String} the user to set.
		 * @return this {@link Builder}
		 */
		public Builder user(final String user) {
			this.user = user;
			return this;
		}

		/**
		 * Setter of password.
		 *
		 * @param password
		 *            {@link String} the password to set.
		 * @return this {@link Builder}
		 */
		public Builder userPassword(final String userPassword) {
			this.userPassword = userPassword;
			return this;
		}

		/**
		 * build new {@link ConnectionDataBase}.
		 *
		 * @return new {@link ConnectionDataBase} from this {@link Builder}
		 */
		public ConnectionDataBase build() {
			return new ConnectionDataBase(this);
		}

	}

	/**
	 * Getter of identifier.
	 *
	 * @return {@link String} the identifier
	 */
	public Identifier getIdentifier() {
		return identifier;
	}

	/**
	 * Getter of host.
	 *
	 * @return {@link String} the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Getter of schema.
	 *
	 * @return {@link String} the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * Getter of user.
	 *
	 * @return {@link String} the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Getter of userPassword.
	 *
	 * @return {@link String} the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof ConnectionDataBase)) {
			return false;
		}
		ConnectionDataBase database = (ConnectionDataBase) obj;
		return com.nordnet.common.valueObject.utils.Equals.TRUE.andEquals(getIdentifier(), database.getIdentifier())
				.andEquals(getHost(), database.getHost()).andEquals(getSchema(), database.getSchema())
				.andEquals(getUser(), database.getUser()).andEquals(getUserPassword(), database.getUserPassword())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new com.nordnet.common.valueObject.utils.HashCode(null).append(getIdentifier()).append(getHost())
				.append(getSchema()).append(getUser()).append(getUserPassword()).hashCode();
	}

	@Override
	public String toString() {
		try {
			return com.nordnet.common.valueObject.utils.JSon.toPrettyJson(this);
		} catch (com.fasterxml.jackson.core.JsonProcessingException jpe) {
			LoggerFactory.getLogger(ConnectionDataBase.class).error("error with toString", jpe);
			return super.toString();
		}
	}

	@Override
	public int compareTo(final ConnectionDataBase o) {
		return new com.nordnet.common.valueObject.utils.Compare(true, true)
				.andCompare(getIdentifier(), o.getIdentifier()).andCompare(getHost(), o.getHost())
				.andCompare(getSchema(), o.getSchema()).andCompare(getUser(), o.getUser())
				.andCompare(getUserPassword(), o.getUserPassword()).compare();
	}

}
