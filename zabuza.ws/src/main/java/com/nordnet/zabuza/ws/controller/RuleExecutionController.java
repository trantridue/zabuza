package com.nordnet.zabuza.ws.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nordnet.zabuza.services.ApplyRuleService;
import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.common.wadl.WadlController;
import com.wordnik.swagger.annotations.Api;

/**
 * Web Service interface for rule execution
 *
 * @author hdoanthanh
 */

@Api(value = "ApplicationWatcher", description = "zabuza API")
@Controller
@RequestMapping("/process/")
public class RuleExecutionController extends WadlController {

	@Autowired
	private ApplyRuleService applyRuleService;

	@RequestMapping(value = "watchRule/{identifier}", method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public String executeRuleByCode(@PathVariable("identifier") final String identifier) {
		Identifier id = Identifier.build(identifier);

		return applyRuleService.forRuleCode(id).toNagios();
	}

}
