package ensolvers.todoapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ensolvers.todoapp.model.Folder;
import ensolvers.todoapp.model.Item;
import ensolvers.todoapp.model.User;
import ensolvers.todoapp.templates.GenericRepository;

@Repository
public interface ItemRepository extends GenericRepository<Item> {

	List<Item> findItemsByFolderNameIgnoreCaseAndFolderUser(String folderName, User user);

	@Query(value= "Select f from Folder f where upper(f.name) = upper(:folderName) and "
			+ "f.user = :user")
	Folder findFolder(String folderName, User user);
}
