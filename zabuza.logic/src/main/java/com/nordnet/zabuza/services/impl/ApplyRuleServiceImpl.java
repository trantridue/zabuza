package com.nordnet.zabuza.services.impl;

import static com.nordnet.common.valueObject.utils.Null.checkExist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.zabuza.business.ResponseRule;
import com.nordnet.zabuza.domain.model.ConnectionDataBase;
import com.nordnet.zabuza.domain.model.Rule;
import com.nordnet.zabuza.domain.repository.RuleRepository;
import com.nordnet.zabuza.services.ApplyRuleService;

@Service("applyRuleService")
public class ApplyRuleServiceImpl implements ApplyRuleService {

	/** {@link RuleRepository} ruleRepository. */
	@Autowired
	private RuleRepository ruleRepository;

	/** {@link Map<Identifier,EntityManager>} entityManagers. */
	protected static Map<Identifier, EntityManager> entityManagers = new HashMap<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseRule forRuleCode(final Identifier ruleCode) {

		Rule rule = ruleRepository.findByIdentifier(ruleCode);
		checkExist("rule", rule, ruleCode);

		List<?> matchedResults = new ArrayList<>();
		List<Map<String, String>> unmatchedResult = new ArrayList<>();

		List<Map<String, String>> parameters = new ArrayList<>();

		ResponseRule parentResponseRule = null;
		if (rule.getParent() != null) {
			parentResponseRule = forRuleCode(rule.getParent().getIdentifier());
			parameters = parentResponseRule.getResults();
			// add grandpa results
			// if (parentResponseRule.getParentResponse() != null) {
			// parameters.addAll(parentResponseRule.getParentResponse().getResults());
			// }
		}

		EntityManager em = getEntityManager(rule.getDataBase());
		javax.persistence.Query sqlQuery = em.createNativeQuery(rule.getQuery().getSql());

		if (parameters.isEmpty()) {
			matchedResults = sqlQuery.getResultList();
		}
		for (Map<String, String> parameter : parameters) {
			sqlQuery = em.createNativeQuery(rule.getQuery().getSql());
			for (Entry<String, String> entry : parameter.entrySet()) {
				if (rule.getQuery().getSql().contains(":" + entry.getKey())) {
					String parameterValue = entry.getValue();
					sqlQuery.setParameter(entry.getKey(), parameterValue);
				}
			}
			List tmp = sqlQuery.getResultList();
			if (tmp.isEmpty()) {
				unmatchedResult.add(parameter);
			}
			matchedResults.addAll(tmp);
		}

		return ResponseRule.builder().rule(rule)
				.results(getResultFromResultList(rule.getQuery().getResult(), matchedResults))
				.unmatchedRuleDetail(unmatchedResult).parentResponse(parentResponseRule).build();

	}

	@SuppressWarnings("deprecation")
	private static EntityManager getEntityManager(final ConnectionDataBase dataBase) {

		EntityManager em = entityManagers.get(dataBase.getIdentifier());
		if (em == null) {
			Map<String, String> properties = new HashMap<>();
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
			properties
					.put("javax.persistence.jdbc.url",
							"jdbc:mysql://"
									+ dataBase.getHost()
									+ ":3306/"
									+ dataBase.getSchema()
									+ "?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull&useUnicode=yes&characterEncoding=UTF-8&autoReconnect=true");
			properties.put("javax.persistence.jdbc.user", dataBase.getUser());
			properties.put("javax.persistence.jdbc.password", dataBase.getUserPassword());

			LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
			emf.setPersistenceProviderClass(org.hibernate.ejb.HibernatePersistence.class);
			emf.setPackagesToScan("com.nordnet.aw");
			emf.setPersistenceUnitName("opale");
			emf.setJpaPropertyMap(properties);
			emf.afterPropertiesSet();
			em = emf.getObject().createEntityManager();
			entityManagers.put(dataBase.getIdentifier(), em);
		}
		return em;
	}

	private static List<Map<String, String>> getResultFromResultList(final List<String> resultName,
			final List<?> resultList) {

		List<Map<String, String>> results = new ArrayList<>();
		for (Object object : resultList) {

			Map<String, String> singleResult = new HashMap<>();

			// convert ? from resultList to object[]
			Object[] tmp = new Object[]{ object };
			if (object.getClass().isArray()) {
				tmp = (Object[]) object;
			}
			for (int i = 0; i < resultName.size(); i++) {
				singleResult.put(resultName.get(i), String.valueOf(tmp[i]));
			}

			results.add(singleResult);
		}
		return results;
	}
}
