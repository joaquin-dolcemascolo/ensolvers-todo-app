package ensolvers.todoapp.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ensolvers.todoapp.templates.GenericEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements GenericEntity<User>, UserDetails {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @JsonIgnore
    private String password;
    private String username;
    
    @JsonIgnore
    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private Set<Folder> folders;
    
	@Override
	public void update(User updatedUser) {
		this.password = updatedUser.password;
		this.username = updatedUser.username;
		this.folders = updatedUser.folders;
	}
	
	@Override
	public User createNew() {
		User newUser = new User();
		newUser.update(this);
		
		return newUser;
	}
	
	@JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
	
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
	
}
