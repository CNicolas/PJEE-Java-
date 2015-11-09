package pjee.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import pjee.helper.PjeeConstants;
import pjee.helper.PjeeContext;
import pjee.helper.forum.PjeeForumHelper;
import pjee.model.entities.Profile;
import pjee.model.repositories.AuthoritiesRepository;
import pjee.model.repositories.ProfileRepository;
import pjee.model.repositories.UsersRepository;
import pjee.model.repositories.forum.CategoryForumRepository;
import pjee.model.repositories.forum.PostForumRepository;
import pjee.model.repositories.forum.ThemeForumRepository;
import pjee.model.repositories.forum.UnreadPostForumRepository;

@Controller
public abstract class PjeeController implements PjeeConstants {

	@Autowired
	protected PjeeContext				context;
	@Autowired
	protected PjeeForumHelper			forumHelper;
	@Autowired
	public UsersRepository				usersRepository;
	@Autowired
	public AuthoritiesRepository		authoritiesRepository;
	@Autowired
	public ProfileRepository			profileRepository;
	@Autowired
	protected ThemeForumRepository		themeForumRepository;
	@Autowired
	protected CategoryForumRepository	categoryForumRepository;
	@Autowired
	protected PostForumRepository		postForumRepository;
	@Autowired
	protected UnreadPostForumRepository	unreadPostForumRepository;

	private final Logger logger;

	public PjeeController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Remplit le model avec les valeurs par défaut.
	 * 
	 * @param model
	 *            le model
	 */
	@ModelAttribute
	public void populateModel(Model model) {
		Profile profile = context.getProfile();
		if (profile != null) {
			model.addAttribute("profile", profile);
		}

		// Setting Model
		model.addAttribute("title", makeTitle());
		model.addAttribute("connected", context.isConnected());

		// Logging
		logger.debug("Remplissage du model global");
	}

	private String makeTitle() {
		// Logging
		logger.debug("Calcul du titre de la page");

		String className = this.getClass().getSimpleName();
		switch (className) {
			case "IndexController":
				return INDEX_TITLE;
			case "ConnectionController":
				return CONNECTION_TITLE;
			case "AccountController":
				return ACCOUNT_TITLE;
			case "PjeeErrorController":
				return ERROR_TITLE;
			case "ForumController":
				return FORUM_TITLE;
			case "NewPostController":
				return NEWPOST_TITLE;
			case "EditPostController":
				return EDITPOST_TITLE;
			case "NewCategoryController":
				return NEWCATEGORY_TITLE;
			default:
				return DEFAULT_TITLE;
		}
	}
}
