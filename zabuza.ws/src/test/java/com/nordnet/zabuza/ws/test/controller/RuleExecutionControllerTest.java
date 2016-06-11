package com.nordnet.zabuza.ws.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nordnet.zabuza.ws.test.GlobalTestCase;

/**
 * test case for BatchController.
 *
 * @author MMORTIER
 *
 */
@ContextConfiguration(locations = { "/spring-mvc-servlet.xml" })
public class RuleExecutionControllerTest extends GlobalTestCase {

	/**
	 * getWadl.
	 *
	 */
	@Test
	public void getWadl() {
		try {
			assertNotNull(getMockMvc().perform(get("/process/application.wadl")).andExpect(status().isOk()).andReturn()
					.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("no exception was expected here " + e.getMessage());
		}
	}

	/**
	 * test execute rule.
	 *
	 */
	@Test
	@DatabaseSetup(value = "/dataset/initDb.xml")
	public void executeRule() {
		try {
			String response =
					getMockMvc().perform(get("/process/watchRule/check_connection")).andExpect(status().isOk())
							.andReturn().getResponse().getContentAsString();
			assertEquals("check_connection;0;check all connection are used|nbResult=1 total=1", response);
		} catch (Exception e) {
			Assert.fail("no exception was expected here : " + e);
		}

		// not found rule
		try {
			String response =
					getMockMvc().perform(get("/process/watchRule/unknown")).andExpect(status().isNotFound())
							.andReturn().getResponse().getContentAsString();
			assertEquals(
					"{\"url\":\"/process/watchRule/unknown\",\"errorCode\":\"0.1.2\",\"errorMessage\":\"{\\\"type\\\":\\\"not found\\\",\\\"object\\\":\\\"rule\\\",\\\"explain\\\":\\\"rule reference by unknown does not exist.\\\"}\"}",
					response);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("no exception was expected here : " + e);
		}

	}
}
