package com.nordnet.zabuza.domain.services;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.domain.services.AddConnectionDataBaseService.AbstractAddConnectionDataBaseLauncher.Builder;

public interface AddConnectionDataBaseService {

	/**
	 * The Class AddRejectLauncher.
	 */
	public abstract class AbstractAddConnectionDataBaseLauncher implements AddConnectionDataBaseService {

		/**
		 * Execute.
		 *
		 * @param builder
		 *            the builder
		 * @return the reject
		 */
		protected ConnectionDataBase execute(final Builder builder) {
			return addConnectionDataBase(builder);
		}

		@Override
		public Builder launcher() {
			return new Builder(this);
		}

		/**
		 * The Class Builder.
		 */
		public static class Builder {

			/** The add rule launcher. */
			private final AbstractAddConnectionDataBaseLauncher addConnectionDataBaseLauncher;

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

			/**
			 * Instantiates a new builder.
			 *
			 * @param addRejectLauncher
			 *            the add reject launcher
			 */
			Builder(final AbstractAddConnectionDataBaseLauncher addConnectionDataBaseLauncher) {
				this.addConnectionDataBaseLauncher = addConnectionDataBaseLauncher;
			}

			/**
			 * Getter of identifier.
			 *
			 * @return {@link Identifier} the identifier
			 */
			public Identifier getIdentifier() {
				return identifier;
			}

			/**
			 * Setter of identifier.
			 *
			 * @param identifier
			 *            {@link Identifier} the identifier to set.
			 */
			public Builder identifier(final Identifier identifier) {
				this.identifier = identifier;
				return this;
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
			 * Setter of host.
			 *
			 * @param host
			 *            {@link String} the host to set.
			 */
			public Builder host(final String host) {
				this.host = host;
				return this;
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
			 * Setter of schema.
			 *
			 * @param schema
			 *            {@link String} the schema to set.
			 */
			public Builder schema(final String schema) {
				this.schema = schema;
				return this;
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
			 * Setter of user.
			 *
			 * @param user
			 *            {@link String} the user to set.
			 */
			public Builder user(final String user) {
				this.user = user;
				return this;
			}

			/**
			 * Getter of userPassword.
			 *
			 * @return {@link String} the userPassword
			 */
			public String getUserPassword() {
				return userPassword;
			}

			/**
			 * Setter of userPassword.
			 *
			 * @param userPassword
			 *            {@link String} the userPassword to set.
			 */
			public Builder userPassword(final String userPassword) {
				this.userPassword = userPassword;
				return this;
			}

			/**
			 * Execute.
			 *
			 * @return the ConnectionDataBase
			 */
			public ConnectionDataBase execute() {
				return addConnectionDataBaseLauncher.execute(this);
			}
		}

		/**
		 * Adds the ConnectionDataBase.
		 *
		 * @param builder
		 *            the builder
		 * @return the reject
		 */
		protected abstract ConnectionDataBase addConnectionDataBase(Builder builder);
	}

	/**
	 * Launcher.
	 *
	 * @return the builder
	 */
	public Builder launcher();
}
