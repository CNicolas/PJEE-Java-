package pjee.helper.forum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import pjee.helper.PjeeContext;
import pjee.model.dto.forum.PostForumDto;
import pjee.model.entities.Profile;
import pjee.model.entities.forum.CategoryForum;
import pjee.model.entities.forum.PostForum;
import pjee.model.entities.forum.ThemeForum;
import pjee.model.entities.forum.UnreadPostForum;
import pjee.model.repositories.forum.CategoryForumRepository;
import pjee.model.repositories.forum.PostForumRepository;
import pjee.model.repositories.forum.UnreadPostForumRepository;

@Service
public class PjeeForumHelper {

	@Autowired
	private CategoryForumRepository		categoryForumRepository;
	@Autowired
	private PostForumRepository			postForumRepository;
	@Autowired
	private UnreadPostForumRepository	unreadPostForumRepository;

	@Autowired
	protected PjeeContext context;

	/**
	 * Get all the posts of a theme.
	 * 
	 * @param theme
	 *            the theme
	 * @return a list of the posts
	 */
	public List<PostForum> getPostsByTheme(ThemeForum theme) {
		List<PostForum> res = new ArrayList<>();
		List<CategoryForum> categories = categoryForumRepository.findByTheme(theme);
		for (CategoryForum categoryForum : categories) {
			res.addAll(postForumRepository.findAllByCategory(categoryForum));
		}
		return res;
	}

	/**
	 * Add to unread posts.
	 */
	public void saveUnreadPosts(List<PostForum> posts) {
		for (PostForum post : posts) {
			Profile profile = context.getProfile();
			if (profile != null && profile.getLastLoginDate().before(post.getTimestamp())
					&& !post.getProfile().equals(profile)) {
				UnreadPostForum res = unreadPostForumRepository.findOneByProfileAndPostForum(profile, post);
				if (res == null) {
					res = new UnreadPostForum();
					res.setProfile(profile);
					res.setPostForum(post);
					unreadPostForumRepository.save(res);
				}
			}
		}
	}

	public void saveAllUnreadPosts() {
		List<PostForum> posts = postForumRepository.findAll();
		saveUnreadPosts(posts);
	}

	/**
	 * Remove from UnreadPosts.
	 * 
	 * @param posts
	 *            the now read posts
	 */
	public void saveReadPosts(List<PostForum> posts) {
		for (PostForum post : posts) {
			Profile profile = context.getProfile();
			UnreadPostForum ur = unreadPostForumRepository.findOneByProfileAndPostForum(profile, post);
			if (ur != null) {
				unreadPostForumRepository.delete(ur);
			}
		}
	}

	/**
	 * Return a map indexed by categories from a list of posts.
	 * 
	 * @param list
	 *            the list
	 * @return the map
	 */
	public Multimap<CategoryForum, PostForum> postsListToMapByCategory(List<PostForum> list) {
		if (list.isEmpty()) {
			return null;
		}

		Multimap<CategoryForum, PostForum> res = ArrayListMultimap.create();
		for (PostForum postForum : list) {
			res.put(postForum.getCategory(), postForum);
		}

		return res;
	}

	public List<PostForumDto> postListToPostDtoList(List<PostForum> posts) {
		List<PostForumDto> res = new ArrayList<>();

		int len = posts.size();
		for (int i = 0; i < len; i++) {
			res.add(new PostForumDto(posts.get(i)));
		}

		return res;
	}

	public String getImageFromProfile(Profile profile) {
		String currentDir = System.getProperty("user.dir");
		Path path = Paths.get(currentDir + "/img/" + profile.getImage());
		if (Files.isRegularFile(path)) {
			try {
				String image = new String(Base64.getEncoder().encode(Files.readAllBytes(path)));
				return "data:image/png;base64," + image;
			} catch (IOException e) {
				return null;
			}
		}
		return null;
	}

}
