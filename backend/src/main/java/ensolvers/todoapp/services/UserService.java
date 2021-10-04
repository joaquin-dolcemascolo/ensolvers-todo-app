package ensolvers.todoapp.services;

import ensolvers.todoapp.repositories.UserRepository;
import ensolvers.todoapp.templates.GenericService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ensolvers.todoapp.model.User;

@Service
public class UserService extends GenericService<User> {

	public UserService(UserRepository repository) {
		super(repository);
	}
	
	public User getLoggedUser() {
    	return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
