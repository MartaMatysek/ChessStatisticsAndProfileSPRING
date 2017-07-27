package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.ValidationUserService;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserProfileTO;

@Service
public class UserValidationServiceImpl implements ValidationUserService{
	
	@Autowired
	private UserDao statisticsDao;
	
	public void validate(Long id) throws UserValidationException{
		UserProfileTO findById = statisticsDao.findById(id);
		if(findById == null){
			throw new UserValidationException("User with given ID does not exist!");
		}
	}
}
