package com.nordnet.zabuza.ws.test.controller;

import static com.nordnet.zabuza.ws.entities.constants.Level.OK;
import static com.nordnet.common.valueObject.utils.JSon.toJson;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.nordnet.zabuza.ws.entities.ConnectionDataBase;
import com.nordnet.zabuza.ws.entities.Query;
import com.nordnet.zabuza.ws.entities.Rule;
import com.nordnet.zabuza.ws.test.GlobalTestCase;
import com.nordnet.common.valueObject.identifier.Identifier;
import com.nordnet.common.valueObject.utils.JSon;

/**
 * test case for BatchController.
 *
 * @author MMORTIER
 *
 */
@ContextConfiguration(locations = { "/spring-mvc-servlet.xml" })
public class AdminControllerTest extends GlobalTestCase {

	/**
	 * getWadl.
	 *
	 */
	@Test
	public void getWadl() {
		try {
			assertNotNull(getMockMvc().perform(get("/admin/application.wadl")).andExpect(status().isOk()).andReturn()
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
	@DatabaseSetup(value = "/dataset/emptyDb.xml")
	public void successfulTest() {
		// add connection
		ConnectionDataBase connectionDataBase =
				ConnectionDataBase.builder().host("host").identifier(Identifier.build("connectionDataBaseId"))
						.schema("schema").user("user").userPassword("userPassword").build();
		try {
			getMockMvc()
					.perform(
							put("/admin/connectionDataBase").contentType(APPLICATION_JSON).content(
									toJson(connectionDataBase)))
					.andExpect(status().isCreated())
					.andExpect(
							MockMvcResultMatchers.header()
									.string("Location",
											org.hamcrest.CoreMatchers
													.equalTo("/admin/connectionDataBase/connectionDataBaseId")));
			// search all
			getMockMvc().perform(get("/admin/connectionDataBase")).andExpect(status().isOk());
			// search one
			getMockMvc().perform(get("/admin/connectionDataBase/connectionDataBaseId")).andExpect(status().isOk());
			// serach unknown
			getMockMvc().perform(get("/admin/connectionDataBase/unknown")).andExpect(status().isNotFound());
		} catch (Exception e) {
			Assert.fail("no exception was expected here : " + e);
		}

		// add query
		Query query =
				Query.builder().identifier(Identifier.build("queryId")).result(Arrays.asList("result")).sql("sql")
						.build();
		try {
			getMockMvc()
					.perform(put("/admin/query").contentType(APPLICATION_JSON).content(toJson(query)))
					.andExpect(status().isCreated())
					.andExpect(
							MockMvcResultMatchers.header().string("Location",
									org.hamcrest.CoreMatchers.equalTo("/admin/query/queryId")));
			// search all
			getMockMvc().perform(get("/admin/query")).andExpect(status().isOk());
			// search one
			getMockMvc().perform(get("/admin/query/queryId")).andExpect(status().isOk());
			// serach unknown
			getMockMvc().perform(get("/admin/query/unknown")).andExpect(status().isNotFound());

		} catch (Exception e) {
			Assert.fail("no exception was expected here : " + e);
		}

		// add rule
		Rule rule =
				Rule.builder().connectionDataBase(Identifier.build("connectionDataBaseId")).descriptif("descriptif")
						.identifier(Identifier.build("ruleId")).level(OK).query(Identifier.build("queryId")).build();
		try {
			getMockMvc()
					.perform(put("/admin/rule").contentType(MediaType.APPLICATION_JSON).content(JSon.toJson(rule)))
					.andExpect(status().isCreated())
					.andExpect(
							MockMvcResultMatchers.header().string("Location",
									org.hamcrest.CoreMatchers.equalTo("/admin/rule/ruleId")));
			// search all
			getMockMvc().perform(get("/admin/rule")).andExpect(status().isOk());
			// search one
			getMockMvc().perform(get("/admin/rule/ruleId")).andExpect(status().isOk());
			// serach unknown
			getMockMvc().perform(get("/admin/rule/unknown")).andExpect(status().isNotFound());

		} catch (Exception e) {
			Assert.fail("no exception was expected here : " + e);
		}

	}
}
