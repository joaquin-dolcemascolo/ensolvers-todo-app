package ensolvers.todoapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ensolvers.todoapp.model.Item;
import ensolvers.todoapp.repositories.ItemRepository;

@Service
public class ItemService {
	
	private final ItemRepository repository;
	private final UserService userService;

	@Autowired
	public ItemService(ItemRepository repository, UserService userService) {
		this.repository = repository;
		this.userService = userService;
	}
	
	public List<Item> findByFolder(String folderName) {
		return this.repository.findItemsByFolderNameIgnoreCaseAndFolderUser(
				folderName, this.userService.getLoggedUser());
	}
	
	public Item add(Item newItem) {
		newItem.setFolder(this.repository.findFolder(
				newItem.getFolder().getName(), this.userService.getLoggedUser()));
		return this.repository.save(newItem.createNew());
	}
	
	public Item update(Item updatedItem) {
		Item dbItem = this.repository.getById(updatedItem.getId());
		dbItem.update(updatedItem);
		return this.repository.save(dbItem);
	}
	
	public void delete(Long folderID) {
		this.repository.deleteById(folderID);
	}
	
}
