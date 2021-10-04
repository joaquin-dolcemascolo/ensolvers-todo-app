package ensolvers.todoapp.templates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ensolvers.todoapp.model.Folder;

public abstract class GenericService<T extends GenericEntity<T>> {
	
	protected final GenericRepository<T> repository;
	
	@Autowired
    public GenericService(GenericRepository<T> repository) {
		this.repository = repository;
	}
    
    public T getEntity(Long ID) {
    	return this.repository.findById(ID).orElse(null);
    }

	public List<T> getAll() {
		return this.repository.findAll();
	}

    @Transactional
	public T add(T newEntity) {
    	T dbEntity = newEntity.createNew();
		return this.repository.save(dbEntity);
	}
    
    @Transactional
	public T update(T updatedEntity) {
		T dbEntity = this.getEntity(updatedEntity.getId());
		dbEntity.update(updatedEntity);
		return this.repository.save(dbEntity);
	}
    
    @Transactional
	public void delete(Long ID) {
		this.repository.deleteById(ID);
	}

}
