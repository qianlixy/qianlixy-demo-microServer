package io.qianlixy.github.demo.microserver.eureka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EurekaApplication.class)
public class BaseTest {

	final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void test() {
		log.info("This is a test");
		try {
			int i = 1 / 0;
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
