package pjee.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pjee.model.entities.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

	public Profile findOneByUser_username(String username);

	public Profile findByEmail(String email);

	@Query("select p from Profile p where p.firstname like %?1% or p.lastname like %?1% or p.email like %?1%")
	public List<Profile> search(String recherche);

}
