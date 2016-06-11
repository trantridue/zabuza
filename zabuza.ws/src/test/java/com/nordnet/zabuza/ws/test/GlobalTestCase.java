package com.nordnet.zabuza.ws.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.nordnet.zabuza.ws.test.utils.NordnetDataSetLoader;

/**
 * Abstract class for Test classes.
 * <p>
 * All test classes must inherit this class.
 *
 * @author jldansou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "/applicationContextWebTest.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = NordnetDataSetLoader.class)
@Ignore
public class GlobalTestCase {

	/**
	 * Default logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalTestCase.class);

	/** {@link WebApplicationContext} wac. */
	@Autowired
	private WebApplicationContext wac;

	/** {@link MockMvc} mockMvc. */
	private MockMvc mockMvc;

	/**
	 * setup.
	 *
	 */
	@Before
	public void setup() {
		setMockMvc(MockMvcBuilders.webAppContextSetup(wac).build());
	}

	/**
	 * Memory dump.
	 */
	public static void afterClass() {

		LOGGER.info("Free memory  : " + Runtime.getRuntime().freeMemory() + " bytes");
		LOGGER.info("Max memory   : " + Runtime.getRuntime().maxMemory() + " bytes");
		LOGGER.info("Total memory : " + Runtime.getRuntime().totalMemory() + " bytes");
	}

	/**
	 * @return the mockMvc
	 */
	public MockMvc getMockMvc() {
		return mockMvc;
	}

	/**
	 * @param mockMvc
	 *            the mockMvc to set
	 */
	public void setMockMvc(final MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

}
