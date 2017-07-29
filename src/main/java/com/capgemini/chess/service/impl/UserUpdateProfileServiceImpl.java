package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.UserUpdateProfileService;
import com.capgemini.chess.service.UserValidationService;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserProfileTO;

@Service
public class UserUpdateProfileServiceImpl implements UserUpdateProfileService{
	
	@Autowired
	private UserValidationService userValidation;
	@Autowired
	private UserDao userDao;

	@Override
	public UserProfileTO update(UserProfileTO userProfileTO) throws UserValidationException {
		userValidation.validate(userProfileTO);
		return userDao.updateProfile(userProfileTO);
	}
}
