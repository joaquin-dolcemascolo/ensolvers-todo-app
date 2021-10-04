package ensolvers.todoapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ensolvers.todoapp.model.Folder;
import ensolvers.todoapp.model.User;
import ensolvers.todoapp.templates.GenericRepository;

@Repository
public interface FolderRepository extends GenericRepository<Folder> {

	List<Folder> findByUser(User loggedUser);
	Optional<Folder> findByNameIgnoreCaseAndUser(String name, User loggedUser);

}
