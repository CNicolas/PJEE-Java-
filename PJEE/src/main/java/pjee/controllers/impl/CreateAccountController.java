package pjee.controllers.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pjee.controllers.PjeeController;
import pjee.exception.PjeeDatabaseException;
import pjee.helper.enums.Role;
import pjee.model.dto.forum.FormCreateAccountDto;
import pjee.model.entities.Authorities;
import pjee.model.entities.Profile;
import pjee.model.entities.Users;

@Controller
public class CreateAccountController extends PjeeController {

	private final Logger logger;

	public CreateAccountController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Show the registration page.
	 * 
	 * @param model
	 *            the model
	 * @return the page
	 */
	@RequestMapping(value = { CREATEACCOUNT_PAGE, ROOT_CREATEACCOUNT_PAGE }, method = RequestMethod.GET)
	public String create_account_index(Model model) {
		FormCreateAccountDto form = new FormCreateAccountDto();
		model.addAttribute("form", form);
		return CREATEACCOUNT_PAGE;
	}

	/**
	 * Handle the registration submission.
	 * 
	 * @param model
	 *            the model
	 * @param form
	 *            the object containing username, password, firstname, lastname, email and age
	 * @return the index page
	 */
	@RequestMapping(value = { CREATEACCOUNT_PAGE, ROOT_CREATEACCOUNT_PAGE }, method = RequestMethod.POST)
	public String create_account(Model model, @ModelAttribute FormCreateAccountDto form) {
		try {
			// Check
			checkUserByUsername(form);

			// Prepare the database save
			Users newUser = new Users();
			newUser.setUsername(form.getUser());
			newUser.setPassword(form.getPassword());
			newUser.setEnabled(true); // Validation du compte ?

			Authorities newAuthority = new Authorities();
			newAuthority.setUsername(form.getUser());
			newAuthority.setAuthority(Role.USER.getName());

			Profile newProfile = new Profile();
			newProfile.setEmail(form.getEmail());
			newProfile.setFirstname(form.getFirstname());
			newProfile.setLastname(form.getLastname());
			newProfile.setAge(form.getAge());
			newProfile.setLastLoginDate(new Timestamp(new Date().getTime() - 1000));
			newProfile.setUser(newUser);

			// Database save
			usersRepository.save(newUser);
			authoritiesRepository.save(newAuthority);
			profileRepository.save(newProfile);

			// Logging
			logger.info("Utilisateur créé");

			return "redirect:" + ROOT_INDEX_PAGE;
		} catch (PjeeDatabaseException e) {
			// Setting Model
			model.addAttribute("fail", true);
			model.addAttribute("failMessage", e.getMessage());
			model.addAttribute("form", form);

			// Logging
			logger.error("Echec de l'inscription", e);
			return CREATEACCOUNT_PAGE;
		}
	}

	// --------------------------------------------------------------------------
	// PRIVATE METHODS (CHECK)
	// --------------------------------------------------------------------------

	/**
	 * Check if an user already exists with the given username.
	 * 
	 * @param formDto
	 *            the form dto containing informations to check
	 * @throws PjeeDatabaseException
	 *             if a matching has been found
	 */
	private void checkUserByUsername(FormCreateAccountDto formDto) throws PjeeDatabaseException {
		Profile check = profileRepository.findOneByUser_username(formDto.getUser());
		if (check != null) {
			// Logging
			logger.warn("Username déjà utilisé");
			throw new PjeeDatabaseException("Le nom d'utilisateur appartient déjà à quelqu'un d'autre");
		}
	}

}
