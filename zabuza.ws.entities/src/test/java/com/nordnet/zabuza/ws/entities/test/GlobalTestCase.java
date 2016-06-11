package com.nordnet.zabuza.ws.entities.test;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract class for Test classes.
 * <p>
 * All test classes must inherit this class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContextTest.xml")
@Ignore
public class GlobalTestCase {

	/** default Constructor. */
	protected GlobalTestCase() {
		super();
	}
	
	/** logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalTestCase.class);
	
	/** Memory dump. */
	public static void afterClass() {
		LOGGER.info("Free memory  : " + Runtime.getRuntime().freeMemory() + " bytes");
		LOGGER.info("Max memory   : " + Runtime.getRuntime().maxMemory() + " bytes");
		LOGGER.info("Total memory : " + Runtime.getRuntime().totalMemory() + " bytes");
	}
}
