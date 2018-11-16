package io.github.qianlixy.demo.server.cachemanager;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheManagerApplication.class)
@AutoConfigureMockMvc
public abstract class BaseSpringBootJunitTest {

}
