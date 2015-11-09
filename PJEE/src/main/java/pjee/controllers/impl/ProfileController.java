package pjee.controllers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pjee.controllers.PjeeController;
import pjee.model.entities.Profile;

@Controller
public class ProfileController extends PjeeController {

	private final Logger logger;

	public ProfileController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Show a profile.
	 * 
	 * @param model
	 *            the model
	 * @param profileId
	 *            the profile id
	 * @return the profile page
	 */
	@RequestMapping(value = { PROFILE_PAGE }, method = RequestMethod.GET)
	public String profile(Model model, @RequestParam(value = "p", required = true) Long profileId) {
		Profile profile = profileRepository.findOne(profileId);

		// Setting model
		model.addAttribute("profile", profile);
		model.addAttribute("image", forumHelper.getImageFromProfile(profile));
		model.addAttribute("title", profile.getFirstname() + " " + profile.getLastname());

		// Logging
		logger.info("Returns the profile page");

		return PROFILE_PAGE;
	}
}
