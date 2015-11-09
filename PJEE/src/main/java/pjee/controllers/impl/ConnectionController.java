package pjee.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pjee.controllers.PjeeController;

@Controller
public class ConnectionController extends PjeeController {

	private final Logger logger;

	public ConnectionController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Page du formulaire de connexion.
	 * 
	 * @param model
	 *            le model
	 * @return la page
	 */
	@RequestMapping(value = CONNECTION_PAGE, method = RequestMethod.GET)
	public String login(Model model) {
		// Logging
		logger.debug("Connection page");

		return CONNECTION_PAGE;
	}

}
