package pjee.model.repositories.forum;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pjee.model.entities.Profile;
import pjee.model.entities.forum.CategoryForum;
import pjee.model.entities.forum.PostForum;
import pjee.model.entities.forum.ThemeForum;
import pjee.model.entities.forum.UnreadPostForum;

public interface UnreadPostForumRepository extends CrudRepository<UnreadPostForum, Long> {

	public List<UnreadPostForum> findByPostForum(PostForum postforum);

	public List<UnreadPostForum> findByProfile(Profile profile);

	public UnreadPostForum findOneByProfileAndPostForum(Profile profile, PostForum postforum);

	public List<UnreadPostForum> findAllByProfileAndPostForum_category(Profile profile, CategoryForum theme);

	public List<UnreadPostForum> findAllByProfileAndPostForum_category_theme(Profile profile, ThemeForum theme);
}
