package pjee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Classe qui effectue des tests sur le serveur.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PjeeApplication.class)
@WebAppConfiguration
public class PjeeApplicationTests {

	/**
	 * Teste que le contexte spring se charge correctement.
	 */
	@Test
	public void contextLoads() {
	}

}
