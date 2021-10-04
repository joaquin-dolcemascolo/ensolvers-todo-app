package ensolvers.todoapp.templates;

public interface GenericEntity<T> {
	
    Long getId();
    T createNew();
    void update(T updatedEntity);

}