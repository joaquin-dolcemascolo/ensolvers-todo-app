package ensolvers.todoapp.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ensolvers.todoapp.model.User;
import ensolvers.todoapp.repositories.FolderRepository;
import ensolvers.todoapp.repositories.UserRepository;
import ensolvers.todoapp.services.UserService;
import ensolvers.todoapp.templates.GenericController;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends GenericController<User> {

	public UserController(UserService service) {
        super(service);
    }
}
