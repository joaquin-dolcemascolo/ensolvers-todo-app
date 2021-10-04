package ensolvers.todoapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ensolvers.todoapp.model.Folder;
import ensolvers.todoapp.model.Item;
import ensolvers.todoapp.repositories.ItemRepository;
import ensolvers.todoapp.services.FolderService;
import ensolvers.todoapp.services.ItemService;
import ensolvers.todoapp.templates.GenericController;
import lombok.Data;

@RestController
@RequestMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
	
	private final ItemService service;
	
	public ItemController(ItemService service) {
        this.service = service;
    }
	
	@GetMapping("/fromFolder/{folderName}")
	public ResponseEntity<List<Item>> getAll(@PathVariable String folderName) {
		return ResponseEntity.ok(this.service.findByFolder(folderName));
    }
	
	@PostMapping("")
	public ResponseEntity<Item> add(@RequestBody Item newItem) {
		return ResponseEntity.ok(this.service.add(newItem));
    }

    @PutMapping("")
    public ResponseEntity<Item> update(@RequestBody Item updatedItem){
        return ResponseEntity.ok(this.service.update(updatedItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Item correctly removed.");
    }

}
