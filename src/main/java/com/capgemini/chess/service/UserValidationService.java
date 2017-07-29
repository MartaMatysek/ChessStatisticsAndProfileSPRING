package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.UserProfileTO;

public interface UserValidationService {

	void validate(Long id) throws UserValidationException;
		
	void validate(UserProfileTO userProfileTO) throws UserValidationException;
	
}
