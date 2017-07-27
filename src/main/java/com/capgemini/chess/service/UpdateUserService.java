package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.to.UserProfileTO;

public interface UpdateUserService {
	
	UserProfileTO updateProfile(UserProfileTO userProfileTO) throws UserValidationException;
}
