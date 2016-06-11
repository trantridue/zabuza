package com.nordnet.zabuza.domain.services;

import org.springframework.transaction.annotation.Transactional;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.domain.model.Query;
import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.domain.model.constants.Level;
import com.nordnet.zabuza.domain.services.AddRuleService.AbstractAddRuleLauncher.Builder;

public interface AddRuleService {

	/**
	 * The Class AddRejectLauncher.
	 */
	public abstract class AbstractAddRuleLauncher implements AddRuleService {

		/**
		 * Execute.
		 *
		 * @param builder
		 *            the builder
		 * @return the reject
		 */
		@Transactional
		public Rule execute(final Builder builder) {
			return addRule(builder);
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
			private final AbstractAddRuleLauncher addRuleLauncher;

			/** {@link String} identifier. */
			private Identifier identifier;

			/** {@link ConnectionDataBase} dataBase. */
			private Identifier dataBase;

			/** {@link Query} query. */
			private Identifier query;

			/** {@link Level} level. */
			private Level level;

			/** {@link String} descriptif. */
			private String descriptif;

			/** {@link Rule} parent. */
			private Identifier parent;

			/**
			 * Instantiates a new builder.
			 *
			 * @param addRejectLauncher
			 *            the add reject launcher
			 */
			Builder(final AbstractAddRuleLauncher addRuleLauncher) {
				this.addRuleLauncher = addRuleLauncher;
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
			 * Getter of dataBase.
			 *
			 * @return {@link ConnectionDataBase} the dataBase
			 */
			public Identifier getDataBase() {
				return dataBase;
			}

			/**
			 * Setter of dataBase.
			 *
			 * @param dataBase
			 *            {@link ConnectionDataBase} the dataBase to set.
			 */
			public Builder dataBase(final Identifier dataBase) {
				this.dataBase = dataBase;
				return this;
			}

			/**
			 * Getter of query.
			 *
			 * @return {@link Query} the query
			 */
			public Identifier getQuery() {
				return query;
			}

			/**
			 * Setter of query.
			 *
			 * @param query
			 *            {@link Query} the query to set.
			 */
			public Builder query(final Identifier query) {
				this.query = query;
				return this;
			}

			/**
			 * Getter of level.
			 *
			 * @return {@link Level} the level
			 */
			public Level getLevel() {
				return level;
			}

			/**
			 * Setter of level.
			 *
			 * @param level
			 *            {@link Level} the level to set.
			 */
			public Builder level(final Level level) {
				this.level = level;
				return this;
			}

			/**
			 * Getter of descriptif.
			 *
			 * @return {@link String} the descriptif
			 */
			public String getDescriptif() {
				return descriptif;
			}

			/**
			 * Setter of descriptif.
			 *
			 * @param descriptif
			 *            {@link String} the descriptif to set.
			 */
			public Builder descriptif(final String descriptif) {
				this.descriptif = descriptif;
				return this;
			}

			/**
			 * Getter of parent.
			 *
			 * @return {@link Rule} the parent
			 */
			public Identifier getParent() {
				return parent;
			}

			/**
			 * Setter of parent.
			 *
			 * @param parent
			 *            {@link Rule} the parent to set.
			 */
			public Builder parent(final Identifier parent) {
				this.parent = parent;
				return this;
			}

			/**
			 * Execute.
			 *
			 * @return the Rule
			 */
			@Transactional
			public Rule execute() {
				return addRuleLauncher.execute(this);
			}
		}

		/**
		 * Adds the Rule.
		 *
		 * @param builder
		 *            the builder
		 * @return the Rule
		 */
		protected abstract Rule addRule(Builder builder);
	}

	/**
	 * Launcher.
	 *
	 * @return the builder
	 */
	public Builder launcher();
}
