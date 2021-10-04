package ensolvers.todoapp.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import ensolvers.todoapp.services.FolderService;
import ensolvers.todoapp.model.Folder;

@RestController
@RequestMapping(value = "/api/folders", produces = MediaType.APPLICATION_JSON_VALUE)
public class FolderController {
	
	private final FolderService service;
	
	public FolderController(FolderService service) {
        this.service = service;
    }
	
	@GetMapping("")
	public ResponseEntity<List<Folder>> getAll() {
		return ResponseEntity.ok(this.service.findAll());
    }
	
	@PostMapping("")
	public ResponseEntity<Folder> add(@RequestBody Folder newFolder) {
		return ResponseEntity.ok(this.service.add(newFolder));
    }

    @PutMapping("")
    public ResponseEntity<Folder> update(@RequestBody Folder updatedFolder){
        return ResponseEntity.ok(this.service.update(updatedFolder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Folder correctly removed.");
    }

}
