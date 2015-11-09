package pjee.model.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pjee.model.entities.Users;

public interface UsersRepository extends CrudRepository<Users, String> {

	public List<Users> findByUsername(String username);

}
