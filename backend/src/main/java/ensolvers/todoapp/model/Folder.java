package ensolvers.todoapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ensolvers.todoapp.templates.GenericEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "folders")
public class Folder implements GenericEntity<Folder> {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
		
	private String name;
	
	@ManyToOne(targetEntity = User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="folder", fetch=FetchType.EAGER)
	private Set<Item> items;

	@Override
	public void update(Folder updatedFolder) {
		this.name = updatedFolder.name;
		this.user = updatedFolder.user;
	}
	
	@Override
	public Folder createNew() {
		Folder newFolder = new Folder();
		newFolder.update(this);
		
		return newFolder;
	}
}
