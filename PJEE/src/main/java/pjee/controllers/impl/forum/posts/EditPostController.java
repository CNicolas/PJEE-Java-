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
import pjee.model.entities.forum.PostForum;

@Controller
public class EditPostController extends PjeeController {

	private final Logger logger;

	public EditPostController() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * Show Edit post form.
	 * 
	 * @param model
	 *            the model
	 * @param postId
	 *            the post id
	 * @return the page
	 */
	@RequestMapping(value = { ROOT_EDITPOST_PAGE, EDITPOST_PAGE }, method = RequestMethod.GET)
	public String showForm(Model model, @RequestParam(value = "p", required = true) Long postId) {
		// Database Access
		PostForum postforum = postForumRepository.findOne(postId);

		// Setting Model
		model.addAttribute("postforum", postforum);
		model.addAttribute("postId", postId);

		// Logging
		logger.info("GET editpost");
		return EDITPOST_PAGE;
	}

	/**
	 * Save the post modifications.
	 * 
	 * @param model
	 *            the model
	 * @param postId
	 *            the post id
	 * @param postforum
	 *            the modified post
	 * @return the post's forum page
	 */
	@RequestMapping(value = { ROOT_EDITPOST_PAGE, EDITPOST_PAGE }, method = RequestMethod.POST)
	public String savePost(Model model, @RequestParam(value = "p", required = true) Long postId,
			@ModelAttribute PostForum postforum) {

		// DatabaseAccess Read
		PostForum post = postForumRepository.findOne(postId);

		// Prepare the post
		Timestamp actual = new Timestamp(new Date().getTime());
		post.setTimestamp(actual);
		post.setTitle(postforum.getTitle());
		post.setContent(postforum.getContent());
		post.setType(postforum.getType());

		// Database Access Create
		postForumRepository.save(post);

		StringBuffer sb = new StringBuffer("redirect:");
		sb.append(ROOT_FORUM_PAGE);
		sb.append("?t=");
		sb.append(post.getCategory().getTheme().getId());
		sb.append("&c=");
		sb.append(post.getCategory().getId());

		return sb.toString();
	}
}
