package ensolvers.todoapp.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ensolvers.todoapp.model.PasswordResetToken;
import ensolvers.todoapp.repositories.PasswordTokenRepository;

@Service
public class PasswordTokenService {
	private final PasswordTokenRepository passwordTokenRepository;
	
	@Autowired
    public PasswordTokenService(PasswordTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
    }
	
	public String validatePasswordResetToken(String token) {
	    final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
	 
	    return !isTokenFound(passToken) ? "Token invalido"
	            : isTokenExpired(passToken) ? "Token expirado"
	            : null;
	}
	
	public PasswordResetToken findByToken(String token) {
		return passwordTokenRepository.findByToken(token);
	}
	 
	private boolean isTokenFound(PasswordResetToken passToken) {
	    return passToken != null;
	}
	 
	private boolean isTokenExpired(PasswordResetToken passToken) {
	    final Calendar cal = Calendar.getInstance();
	    return passToken.getExpiryDate().before(cal.getTime());
	}
}
