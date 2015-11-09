package pjee.controllers.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pjee.controllers.PjeeController;
import pjee.model.dto.forum.PostForumDto;
import pjee.model.entities.forum.PostForum;

/**
 * Controlleur de la racine de l'application.
 */
@Controller
public class IndexController extends PjeeController {

	private final Logger logger;

	public IndexController() {
		super();
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Renvoie à la Home.
	 * 
	 * @param model
	 *            données de la page
	 * @return nom de la page dans src/main/resources/templates/*.html
	 */
	@RequestMapping(value = { VOID_PAGE, ROOT_PAGE, INDEX_PAGE, ROOT_INDEX_PAGE }, method = RequestMethod.GET)
	public String index(Model model) {
		// Database Access
		List<PostForum> posts = postForumRepository.findTop5ByOrderByTimestampDesc();

		// Setting Model
		if (!posts.isEmpty()) {
			List<PostForumDto> postsDto = forumHelper.postListToPostDtoList(posts);
			model.addAttribute("postforum", postsDto);
		}

		// Logging
		logger.debug("Arrivée à l'accueil");

		return INDEX_PAGE;
	}
}
