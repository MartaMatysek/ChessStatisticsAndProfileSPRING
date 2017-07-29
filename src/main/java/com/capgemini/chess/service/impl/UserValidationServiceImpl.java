package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.UserValidationService;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserProfileTO;

@Service
public class UserValidationServiceImpl implements UserValidationService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public void validate(Long id) throws UserValidationException{
		validateId(id);
	}

	@Override
	public void validate(UserProfileTO userProfileTO) throws UserValidationException {
		validateId(userProfileTO.getId());
		validatePassword(userProfileTO.getPassword());
	}

	private void validateId(Long id) throws UserValidationException {
		UserProfileTO findById = userDao.findById(id);
		if (findById == null) {
			throw new UserValidationException("User with given ID doesn't exist!");
		}
	}
	
	private void validatePassword(String password) throws UserValidationException{
		if(password.length() < 5){
			throw new UserValidationException("Password is too short. It should have at least 5 charactes.");
		}
	}
}