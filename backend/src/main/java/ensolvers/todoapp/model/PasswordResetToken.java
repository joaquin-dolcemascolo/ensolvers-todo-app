package ensolvers.todoapp.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ensolvers.todoapp.model.PasswordResetToken;
import ensolvers.todoapp.model.User;
import lombok.Data;

@Data
@Entity
public class PasswordResetToken {
 
    private static final int EXPIRATION = 60 * 1;
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    private String token;
 
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;
 
    private Date expiryDate;
    
    public PasswordResetToken() {
    	
    }
    
    public PasswordResetToken(String token, User user) {
    	this.token = token;
    	this.user = user;
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MINUTE, EXPIRATION);
    	this.expiryDate = calendar.getTime();
    }
}