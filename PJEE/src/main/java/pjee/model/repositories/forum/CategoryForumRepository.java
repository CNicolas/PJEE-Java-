package pjee.model.repositories.forum;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pjee.model.entities.forum.CategoryForum;
import pjee.model.entities.forum.ThemeForum;

public interface CategoryForumRepository extends CrudRepository<CategoryForum, Long> {

	public List<CategoryForum> findByTheme(ThemeForum theme);

}
