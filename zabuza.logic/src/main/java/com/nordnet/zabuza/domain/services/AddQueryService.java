package com.nordnet.zabuza.domain.services;

import java.util.List;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.zabuza.domain.services.AddQueryService.AbstractAddQueryLauncher.Builder;

public interface AddQueryService {

	/**
	 * The Class AddRejectLauncher.
	 */
	public abstract class AbstractAddQueryLauncher implements AddQueryService {

		/**
		 * Execute.
		 *
		 * @param builder
		 *            the builder
		 * @return the reject
		 */
		protected Query execute(final Builder builder) {
			return addQuery(builder);
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
			private final AbstractAddQueryLauncher addQueryLauncher;

			/** {@link Identifier} identifier. */
			protected Identifier identifier;

			/** {@link String} sql. */
			protected String sql;

			/** {@link List<String>} result. */
			protected List<String> result;

			/**
			 * Instantiates a new builder.
			 *
			 * @param addRejectLauncher
			 *            the add reject launcher
			 */
			Builder(final AbstractAddQueryLauncher addQueryLauncher) {
				this.addQueryLauncher = addQueryLauncher;
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
			 * Getter of sql.
			 *
			 * @return {@link String} the sql
			 */
			public String getSql() {
				return sql;
			}

			/**
			 * Setter of sql.
			 *
			 * @param sql
			 *            {@link String} the sql to set.
			 */
			public Builder sql(final String sql) {
				this.sql = sql;
				return this;
			}

			/**
			 * Getter of result.
			 *
			 * @return {@link List<String>} the result
			 */
			public List<String> getResult() {
				return result;
			}

			/**
			 * Setter of result.
			 *
			 * @param result
			 *            {@link List<String>} the result to set.
			 */
			public Builder result(final List<String> result) {
				this.result = result;
				return this;
			}

			/**
			 * Execute.
			 *
			 * @return the Query
			 */
			public Query execute() {
				return addQueryLauncher.execute(this);
			}
		}

		/**
		 * Adds the Query.
		 *
		 * @param builder
		 *            the builder
		 * @return the Query
		 */
		protected abstract Query addQuery(Builder builder);
	}

	/**
	 * Launcher.
	 *
	 * @return the builder
	 */
	public Builder launcher();
}
