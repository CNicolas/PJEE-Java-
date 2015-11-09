package pjee.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pjee.controllers.PjeeController;

/**
 * Controlleur de la page d'erreur.
 */
@Controller
public class PjeeErrorController extends PjeeController implements ErrorController {

	private final Logger logger;

	public PjeeErrorController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Method pour afficher la page d'erreur, et variabiliser le model avec les valeurs de l'url
	 * 
	 * @return nom de la page dans src/main/resources/templates/*.html
	 */
	@RequestMapping(value = ERROR_PAGE, method = RequestMethod.GET)
	public String errorGet(Model model) {
		// Setting Model
		model.addAttribute("timeout", 5000);

		// Logging
		logger.error("Arrivée sur la page d'erreur");

		return ERROR_PAGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
	 */
	@Override
	public String getErrorPath() {
		return ERROR_PAGE;
	}

}
