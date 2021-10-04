package ensolvers.todoapp.templates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class GenericController<T extends GenericEntity<T>> {

	protected final GenericService<T> service;
	
	@Autowired
	public GenericController(GenericService<T> service) {
        this.service = service;
    }
	
	@GetMapping("")
	public ResponseEntity<List<T>> getAll() {
		return ResponseEntity.ok(this.service.getAll());
    }
	
	@PostMapping("")
	public ResponseEntity<T> add(@RequestBody T newEntity) {
		return ResponseEntity.ok(this.service.add(newEntity));
    }

    @PutMapping("")
    public ResponseEntity<T> update(@RequestBody T updatedEntity){
        return ResponseEntity.ok(this.service.update(updatedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Correctly removed.");
    }
}
