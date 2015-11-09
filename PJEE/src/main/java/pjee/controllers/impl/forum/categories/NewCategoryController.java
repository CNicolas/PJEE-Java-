package pjee.controllers.impl.forum.categories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pjee.controllers.PjeeController;
import pjee.model.entities.Profile;
import pjee.model.entities.forum.CategoryForum;
import pjee.model.entities.forum.ThemeForum;

@Controller
public class NewCategoryController extends PjeeController {

	private final Logger logger;

	public NewCategoryController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Show form to add a category.
	 * 
	 * @param model
	 *            the model
	 * @return the form
	 */
	@RequestMapping(value = { ROOT_NEWCATEGORY_PAGE, NEWCATEGORY_PAGE }, method = RequestMethod.GET)
	public String showForm(Model model, @RequestParam(value = "t", required = true) Long themeId) {

		// Setting Model
		model.addAttribute("categoryforum", new CategoryForum());
		model.addAttribute("themeId", themeId);

		// Logging
		logger.info("GET newcategory");
		return NEWCATEGORY_PAGE;
	}

	/**
	 * Post the form and save the category.
	 * 
	 * @param model
	 *            the model
	 * @param categoryforum
	 *            the category
	 * @return the previous forum page
	 */
	@RequestMapping(value = { ROOT_NEWCATEGORY_PAGE, NEWCATEGORY_PAGE }, method = RequestMethod.POST)
	public String saveCategory(Model model, @RequestParam(value = "t", required = true) Long themeId,
			@ModelAttribute CategoryForum categoryforum) {

		// DatabaseAccess Read
		ThemeForum theme = themeForumRepository.findOne(themeId);
		Profile profile = context.getProfile();

		// Prepare the category
		categoryforum.setTheme(theme);
		categoryforum.setProfile(profile);

		// Database Access Create
		categoryForumRepository.save(categoryforum);

		// Database Access Update
		theme.setCategoriesNumber(theme.getCategoriesNumber() + 1);
		themeForumRepository.save(theme);

		// Return
		StringBuffer sb = new StringBuffer("redirect:");
		sb.append(ROOT_FORUM_PAGE);
		sb.append("?t=");
		sb.append(themeId);
		sb.append("&c=");
		sb.append(categoryforum.getId());

		return sb.toString();
	}
}
