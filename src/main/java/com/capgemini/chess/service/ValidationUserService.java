package com.capgemini.chess.service;

import com.capgemini.chess.exception.UserValidationException;

public interface ValidationUserService {

	public void validate(Long id) throws UserValidationException;
}
