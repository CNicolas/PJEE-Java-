package pjee.controllers.impl.forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pjee.controllers.PjeeController;
import pjee.model.dto.forum.CategoryForumDto;
import pjee.model.dto.forum.PostForumDto;
import pjee.model.dto.forum.ThemeForumDto;
import pjee.model.entities.forum.CategoryForum;
import pjee.model.entities.forum.PostForum;
import pjee.model.entities.forum.ThemeForum;
import pjee.model.entities.forum.UnreadPostForum;

@Controller
public class ForumController extends PjeeController {

	private final Logger logger;

	public ForumController() {
		super();
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Liste les themes/catégories/posts.
	 * 
	 * @param model
	 *            le model
	 * @return la page
	 */
	@RequestMapping(value = { ROOT_FORUM_PAGE, FORUM_PAGE }, method = RequestMethod.GET)
	public String themesGet(Model model, @RequestParam(value = "t", required = false) Long themeId,
			@RequestParam(value = "c", required = false) Long categoryId) {

		if (themeId != null) {
			ThemeForum theme = themeForumRepository.findOne(themeId);
			if (categoryId != null) {
				CategoryForum category = categoryForumRepository.findOne(categoryId);
				posts(model, theme, category);
			} else {
				categories(model, theme);
			}
		} else {
			themes(model);
		}

		model.addAttribute("profile", context.getProfile());

		// Logging
		logger.debug("Forum page");

		return FORUM_PAGE;
	}

	private void themes(Model model) {
		// Database Access
		List<ThemeForum> themes = themeForumRepository.findAllByOrderByNameAsc();

		// Setting Model
		List<ThemeForumDto> themesDto = new ArrayList<>();
		if (!themes.isEmpty()) {
			Map<ThemeForumDto, PostForum> mapLastPosts = new HashMap<>();

			for (ThemeForum themeForum : themes) {
				final List<UnreadPostForum> unreadPosts = unreadPostForumRepository
						.findAllByProfileAndPostForum_category_theme(context.getProfile(), themeForum);
				PostForum lastPost = postForumRepository.findFirstByCategory_ThemeOrderByTimestampDesc(themeForum);

				ThemeForumDto themeDto = new ThemeForumDto(themeForum);
				themeDto.setNewPostsNumber(unreadPosts.size());

				themesDto.add(themeDto);
				mapLastPosts.put(themeDto, lastPost);
			}

			model.addAttribute("themesforum", themesDto);
			model.addAttribute("mapLastPosts", mapLastPosts);
		} else {
			model.addAttribute("nothemes", true);
		}
	}

	private void categories(Model model, ThemeForum theme) {
		// Database Access
		List<CategoryForum> categories = categoryForumRepository.findByTheme(theme);

		// Setting Model
		List<CategoryForumDto> categoriesDto = new ArrayList<>();
		if (!categories.isEmpty()) {
			Map<CategoryForumDto, PostForum> mapLastPosts = new HashMap<>();

			for (CategoryForum categoryForum : categories) {
				final List<UnreadPostForum> unreadPosts = unreadPostForumRepository
						.findAllByProfileAndPostForum_category(context.getProfile(), categoryForum);

				PostForum lastPost = postForumRepository.findFirstByCategoryOrderByTimestampDesc(categoryForum);

				CategoryForumDto categoryDto = new CategoryForumDto(categoryForum);
				categoryDto.setNewPostsNumber(unreadPosts.size());

				categoriesDto.add(categoryDto);
				mapLastPosts.put(categoryDto, lastPost);
			}

			model.addAttribute("categoriesforum", categoriesDto);
			model.addAttribute("mapLastPosts", mapLastPosts);
		} else {
			model.addAttribute("nocategories", true);
		}

		model.addAttribute("title", theme.getName());
		model.addAttribute("theme", theme);
	}

	private void posts(Model model, ThemeForum theme, CategoryForum category) {
		// Database Access
		List<PostForum> posts = postForumRepository.findAllByCategory(category);

		// Setting Model
		if (!posts.isEmpty()) {
			forumHelper.saveReadPosts(posts);
			List<PostForumDto> postsDto = forumHelper.postListToPostDtoList(posts);
			model.addAttribute("postforum", postsDto);
		} else {
			model.addAttribute("noposts", true);
		}

		model.addAttribute("title", category.getName());
		model.addAttribute("theme", theme);
		model.addAttribute("category", category);
	}

}
