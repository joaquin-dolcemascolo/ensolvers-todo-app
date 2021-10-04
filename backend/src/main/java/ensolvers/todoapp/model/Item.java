package ensolvers.todoapp.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ensolvers.todoapp.templates.GenericEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "items")
public class Item implements GenericEntity<Item> {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean checked = false;
	
	@ManyToOne(targetEntity = Folder.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="folder_id")
	private Folder folder;
	private String content;
	
	@Override
	public void update(Item updatedItem) {
		this.content = updatedItem.content;
		this.folder = updatedItem.folder;
		this.checked = updatedItem.checked;
	}
	
	@Override
	public Item createNew() {
		Item newItem = new Item();
		newItem.update(this);
		
		return newItem;
	}
	
}
