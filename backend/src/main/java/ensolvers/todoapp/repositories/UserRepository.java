package ensolvers.todoapp.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import ensolvers.todoapp.model.User;
import ensolvers.todoapp.templates.GenericRepository;

@Repository
public interface UserRepository extends GenericRepository<User> {
	
	public Optional<User> findByUsername(String username);

}
