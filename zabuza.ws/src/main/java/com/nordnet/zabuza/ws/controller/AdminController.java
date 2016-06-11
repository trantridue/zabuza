package com.nordnet.zabuza.ws.controller;

import static com.nordnet.common.valueObject.utils.Null.checkExist;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import nordnet.tools.converter.Converter;
import nordnet.tools.converter.exceptions.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.common.wadl.WadlController;
import com.nordnet.zabuza.domain.model.constants.Level;
import com.nordnet.zabuza.domain.repository.ConnectionDataBaseRepository;
import com.nordnet.zabuza.domain.repository.QueryRepository;
import com.nordnet.zabuza.domain.repository.RuleRepository;
import com.nordnet.zabuza.domain.services.AddConnectionDataBaseService;
import com.nordnet.zabuza.domain.services.AddQueryService;
import com.nordnet.zabuza.domain.services.AddRuleService;
import com.nordnet.zabuza.ws.entities.ConnectionDataBase;
import com.nordnet.zabuza.ws.entities.Query;
import com.nordnet.zabuza.ws.entities.Rule;
import com.wordnik.swagger.annotations.Api;

/**
 * Web Service interface for rule execution
 *
 * @author hdoanthanh
 */

@Api(value = "admin", description = "zabuza admin API")
@Controller
@RequestMapping("/admin")
public class AdminController extends WadlController {

	@Autowired
	private RuleRepository ruleRepository;

	@Autowired
	private AddRuleService addRuleService;

	@Autowired
	private QueryRepository queryRepository;

	@Autowired
	private AddQueryService addQueryService;

	@Autowired
	private ConnectionDataBaseRepository connectionDataBaseRepository;

	@Autowired
	private AddConnectionDataBaseService addConnectionDataBaseService;

	@Autowired
	private Converter converter;

	@RequestMapping(value = "rule", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Identifier> findAllRule() {
		List<Identifier> result = new ArrayList<>();
		for (com.nordnet.zabuza.domain.model.Rule rule : ruleRepository.findAll()) {
			result.add(rule.getIdentifier());
		}
		return result;
	}

	@RequestMapping(value = "rule/{ruleId}", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Rule findRule(@PathVariable("ruleId") final String ruleId) throws ConverterException {
		Identifier id = Identifier.build(ruleId);

		com.nordnet.zabuza.domain.model.Rule rule = ruleRepository.findByIdentifier(id);
		checkExist("rule", rule, ruleId);

		return converter.convert(rule, Rule.class);
	}

	@RequestMapping(value = "rule", method = PUT, consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(CREATED)
	@Transactional
	public void addRule(final HttpServletResponse httpServletResponse, @RequestBody final Rule rule) {
		com.nordnet.zabuza.domain.model.Rule result =
				addRuleService.launcher().dataBase(rule.getConnectionDataBase()).descriptif(rule.getDescriptif())
						.identifier(rule.getIdentifier()).level(Level.valueOf(rule.getLevel().name()))
						.parent(rule.getParent()).query(rule.getQuery()).execute();

		URI location =
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{ruleId}").build()
						.expand(result.getIdentifier()).toUri();
		httpServletResponse.setHeader("Location", location.getPath());
	}

	@RequestMapping(value = "query", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> findAllQuery() {
		List<String> result = new ArrayList<>();
		for (com.nordnet.zabuza.domain.model.Query query : queryRepository.findAll()) {
			result.add(query.getIdentifier().getIdentifier());
		}
		return result;
	}

	@RequestMapping(value = "query/{queryId}", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public Query findQuery(@PathVariable("queryId") final String queryId) throws ConverterException {
		Identifier id = Identifier.build(queryId);

		com.nordnet.zabuza.domain.model.Query query = queryRepository.findByIdentifier(id);
		checkExist("query", query, queryId);

		return converter.convert(query, Query.class);
	}

	@RequestMapping(value = "query", method = PUT, consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(CREATED)
	public void addQuery(final HttpServletResponse httpServletResponse, @RequestBody final Query query) {
		com.nordnet.zabuza.domain.model.Query result =
				addQueryService.launcher().identifier(query.getIdentifier()).result(query.getResult())
						.sql(query.getSql()).execute();

		URI location =
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{queryId}").build()
						.expand(result.getIdentifier()).toUri();
		httpServletResponse.setHeader("Location", location.getPath());
	}

	@RequestMapping(value = "connectionDataBase", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Identifier> findAllConnectionDataBase() {
		List<Identifier> result = new ArrayList<>();
		for (com.nordnet.zabuza.domain.model.ConnectionDataBase connectionDataBase : connectionDataBaseRepository
				.findAll()) {
			result.add(connectionDataBase.getIdentifier());
		}
		return result;
	}

	@RequestMapping(value = "connectionDataBase/{connectionDataBaseId}", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public ConnectionDataBase findConnectionDataBase(
			@PathVariable("connectionDataBaseId") final String connectionDataBaseId) throws ConverterException {
		Identifier id = Identifier.build(connectionDataBaseId);

		com.nordnet.zabuza.domain.model.ConnectionDataBase connectionDataBase =
				connectionDataBaseRepository.findByIdentifier(id);
		checkExist("connectionDataBase", connectionDataBase, connectionDataBaseId);

		return converter.convert(connectionDataBase, ConnectionDataBase.class);
	}

	@RequestMapping(value = "connectionDataBase", method = PUT, consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(CREATED)
	public void addConnectionDataBase(final HttpServletResponse httpServletResponse,
			@RequestBody final ConnectionDataBase connectionDataBase) {
		com.nordnet.zabuza.domain.model.ConnectionDataBase result =
				addConnectionDataBaseService.launcher().identifier(connectionDataBase.getIdentifier())
						.host(connectionDataBase.getHost()).schema(connectionDataBase.getSchema())
						.user(connectionDataBase.getUser()).userPassword(connectionDataBase.getUserPassword())
						.execute();

		URI location =
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{connectionDataBaseId}").build()
						.expand(result.getIdentifier()).toUri();
		httpServletResponse.setHeader("Location", location.getPath());
	}

}
