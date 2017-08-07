package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.UpdateProfileTO;

public interface UserValidationService {

	void validate(Long id) throws UserValidationException;
		
	void validate(UpdateProfileTO updateProfileTO) throws UserValidationException;
	
}
