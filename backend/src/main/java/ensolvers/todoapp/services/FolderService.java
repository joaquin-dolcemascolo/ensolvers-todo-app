package ensolvers.todoapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ensolvers.todoapp.exception.ResourceConflictException;
import ensolvers.todoapp.model.Folder;
import ensolvers.todoapp.repositories.FolderRepository;

@Service
public class FolderService {
	
	private final FolderRepository repository;
	private final UserService userService;
	
	public FolderService(FolderRepository repository, UserService userService) {
		this.repository = repository;
		this.userService = userService;
	}
	
	public List<Folder> findAll() {
		return this.repository.findByUser(this.userService.getLoggedUser());
	}

	public Folder add(Folder newFolder) {
		this.isDuplicatedName(newFolder.getName());
		newFolder.setUser(this.userService.getLoggedUser());
		return this.repository.save(newFolder.createNew());
	}
	public Folder update(Folder updatedFolder) {
		this.isDuplicatedName(updatedFolder.getName());
		Folder dbFolder = this.repository.getById(updatedFolder.getId());
		updatedFolder.setUser(dbFolder.getUser());
		dbFolder.update(updatedFolder);
		return this.repository.save(dbFolder);
	}
	
	public void delete(Long folderID) {
		this.repository.deleteById(folderID);
	}
	
	public void isDuplicatedName(String name) {
		Optional<Folder> dbFolder = this.repository.findByNameIgnoreCaseAndUser(name, this.userService.getLoggedUser());
		if(dbFolder.isPresent()) throw new ResourceConflictException("A folder with this name already exists");
	}

}
