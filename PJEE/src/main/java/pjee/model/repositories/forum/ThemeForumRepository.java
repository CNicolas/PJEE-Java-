package pjee.model.repositories.forum;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pjee.model.entities.forum.ThemeForum;

public interface ThemeForumRepository extends CrudRepository<ThemeForum, Long> {

	public List<ThemeForum> findAllByOrderByIdAsc();

	public List<ThemeForum> findAllByOrderByNameAsc();

}
