package pjee.controllers.impl.forum.posts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pjee.controllers.PjeeController;
import pjee.model.entities.forum.CategoryForum;
import pjee.model.entities.forum.PostForum;

@Controller
public class DeletePostController extends PjeeController {

	private final Logger logger;

	public DeletePostController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Delete the given post.
	 * 
	 * @param model
	 *            the model
	 * @param themeId
	 *            the theme id of the post
	 * @param categoryId
	 *            the category id of the post
	 * @param postId
	 *            the post id
	 * @return the forum page of the post
	 */
	@RequestMapping(value = DELETEPOST_PAGE, method = RequestMethod.GET)
	public String deletePost(Model model, @RequestParam(value = "t", required = true) Long themeId,
			@RequestParam(value = "c", required = true) Long categoryId,
			@RequestParam(value = "p", required = true) Long postId) {

		// Database Update
		PostForum post = postForumRepository.findOne(postId);
		CategoryForum category = post.getCategory();
		category.setPostsNumber(category.getPostsNumber() - 1);
		categoryForumRepository.save(category);

		// Database Delete
		postForumRepository.delete(postId);

		// Logging
		logger.debug("Delete post page GET");

		// Prepare return
		StringBuffer sb = new StringBuffer("redirect:");
		sb.append(ROOT_FORUM_PAGE);
		sb.append("?t=");
		sb.append(themeId);
		sb.append("&c=");
		sb.append(categoryId);
		return sb.toString();
	}
}
