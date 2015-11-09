package pjee.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pjee.controllers.PjeeController;

@Controller
public class LogoutController extends PjeeController {

	private final Logger logger;

	public LogoutController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Logout.
	 * 
	 * @param model
	 *            le model
	 * @return la page d'accueil
	 */
	@RequestMapping(value = LOGOUT_PAGE, method = RequestMethod.GET)
	public String logoutGet(Model model) {
		// Logging
		logger.info("Déconnecté");

		return INDEX_PAGE;
	}
}
