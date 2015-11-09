package pjee.controllers.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Multimap;

import pjee.controllers.PjeeController;
import pjee.model.entities.Profile;
import pjee.model.entities.forum.CategoryForum;
import pjee.model.entities.forum.PostForum;

@Controller
public class SearchController extends PjeeController {

	private final Logger logger;

	public SearchController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@RequestMapping(value = { ROOT_SEARCH_PAGE, SEARCH_PAGE }, method = RequestMethod.GET)
	public String recherche() {
		// Logging
		logger.debug("GET on results page redirect to forum");

		return "redirect:" + ROOT_INDEX_PAGE;
	}

	/**
	 * Search in forum.
	 * 
	 * @param model
	 *            the model
	 * @param recherche
	 *            the pattern
	 * @return the results
	 */
	@RequestMapping(value = { ROOT_SEARCH_PAGE, SEARCH_PAGE }, method = RequestMethod.POST)
	public String recherche(Model model, @RequestParam(value = "recherche", required = true) String recherche) {
		List<PostForum> posts = postForumRepository.search(recherche);
		List<Profile> profiles = new ArrayList<>();
		if(context.isConnected()) {	
			profiles = profileRepository.search(recherche);
		}

		if (posts.isEmpty() && profiles.isEmpty()) {
			model.addAttribute("noresults", true);
			model.addAttribute("timeout", 5000);
		} else {
			if (!posts.isEmpty()) {
				Multimap<CategoryForum, PostForum> map = forumHelper.postsListToMapByCategory(posts);
				model.addAttribute("map", map);
			}
			if (!profiles.isEmpty()) {
				model.addAttribute("profiles", profiles);
			}
		}

		model.addAttribute("search", recherche);
		model.addAttribute("title", SEARCH_RESULT_TITLE);
		return SEARCH_PAGE;
	}

}
