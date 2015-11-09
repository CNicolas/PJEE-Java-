package pjee.controllers.impl.forum.posts;

import java.sql.Timestamp;
import java.util.Date;

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
import pjee.model.entities.forum.PostForum;
import pjee.model.entities.forum.ThemeForum;

@Controller
public class NewPostController extends PjeeController {

	private final Logger logger;

	public NewPostController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Show form to add a post.
	 * 
	 * @param model
	 *            the model
	 * @return the form
	 */
	@RequestMapping(value = { ROOT_NEWPOST_PAGE, NEWPOST_PAGE }, method = RequestMethod.GET)
	public String showForm(Model model, @RequestParam(value = "t", required = true) Long themeId,
			@RequestParam(value = "c", required = true) Long categoryId) {

		// Setting Model
		model.addAttribute("postforum", new PostForum());
		model.addAttribute("themeId", themeId);
		model.addAttribute("categoryId", categoryId);

		// Logging
		logger.info("GET newpost");
		return NEWPOST_PAGE;
	}

	/**
	 * Post the form and save the post.
	 * 
	 * @param model
	 *            the model
	 * @param postforum
	 *            the post
	 * @return the previous forum page
	 */
	@RequestMapping(value = { ROOT_NEWPOST_PAGE, NEWPOST_PAGE }, method = RequestMethod.POST)
	public String savePost(Model model, @RequestParam(value = "t", required = true) Long themeId,
			@RequestParam(value = "c", required = true) Long categoryId, @ModelAttribute PostForum postforum) {

		// DatabaseAccess Read
		CategoryForum cat = categoryForumRepository.findOne(categoryId);
		Profile profile = context.getProfile();

		// Prepare the post
		Timestamp actual = new Timestamp(new Date().getTime());
		postforum.setCategory(cat);
		postforum.setTimestamp(actual);
		postforum.setProfile(profile);

		// Database Access Create
		postForumRepository.save(postforum);

		// Database Access Update
		cat.setPostsNumber(cat.getPostsNumber() + 1);

		ThemeForum theme = cat.getTheme();
		theme.setPostsNumber(theme.getCategoriesNumber() + 1);

		categoryForumRepository.save(cat);
		themeForumRepository.save(theme);

		// Return
		StringBuffer sb = new StringBuffer("redirect:");
		sb.append(ROOT_FORUM_PAGE);
		sb.append("?t=");
		sb.append(themeId);
		sb.append("&c=");
		sb.append(categoryId);

		return sb.toString();
	}
}
