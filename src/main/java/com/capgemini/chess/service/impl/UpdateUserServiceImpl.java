package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.UpdateUserService;
import com.capgemini.chess.service.ValidationUserService;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserProfileTO;

@Service
public class UpdateUserServiceImpl implements UpdateUserService{
	
	@Autowired
	private ValidationUserService userValidationService;
	@Autowired
	private UserDao userDao;

	@Override
	public UserProfileTO updateProfile(UserProfileTO userProfileTO) throws UserValidationException {
		userValidationService.validate(userProfileTO);
		return userDao.updateProfile(userProfileTO);
	}
}
