package pjee.model.repositories.forum;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pjee.model.entities.forum.CategoryForum;
import pjee.model.entities.forum.PostForum;
import pjee.model.entities.forum.ThemeForum;

public interface PostForumRepository extends CrudRepository<PostForum, Long> {

	public List<PostForum> findAll();

	public List<PostForum> findAllByCategory(CategoryForum category);

	public List<PostForum> findAllByCategory_Theme(ThemeForum theme);

	public List<PostForum> findTop5ByOrderByTimestampDesc();

	public PostForum findFirstByCategoryOrderByTimestampDesc(CategoryForum category);

	public PostForum findFirstByCategory_ThemeOrderByTimestampDesc(ThemeForum theme);

	@Query("select u from PostForum u where u.title like %?1% or u.content like %?1%")
	public List<PostForum> search(String recherche);

}
