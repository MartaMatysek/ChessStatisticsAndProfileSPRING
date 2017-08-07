package com.capgemini.chess.service;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.impl.UserValidationServiceImpl;
import com.capgemini.chess.service.to.UpdateProfileTO;
import com.capgemini.chess.service.to.UserProfileTO;


@RunWith(MockitoJUnitRunner.class)
public class UserValidationServiceTest {
	
	@Mock
	UserDao userDao;
	
	@Test(expected = UserValidationException.class)
	public void shouldNonExistentIDPlayerValidation() throws UserValidationException{
		//given
		UserValidationService userValidation = new UserValidationServiceImpl(userDao);	
		
		when(userDao.findById(1L)).thenReturn(null);
		
		//when
		userValidation.validate(1L);
		
		//then
		fail("This test should throw UserValidationException.");
	}
	
	@Test
	public void shouldExistentIDPlayerValidation() throws UserValidationException{
		//given
		UserValidationService userValidation = new UserValidationServiceImpl(userDao);
		UserProfileTO userTO = new UserProfileTO();
		userTO.setId(1L);		
	
		when(userDao.findById(1L)).thenReturn(userTO);
		
		//when
		userValidation.validate(1L);
		
		//then
		assertEquals(1, userTO.getId().intValue());
		verify(userDao).findById(userTO.getId());
		
	}
	
	@Test(expected = UserValidationException.class)
	public void shouldWrongPasswordValidation() throws UserValidationException{
		//given
		UserValidationService userValidation = new UserValidationServiceImpl(userDao);
		UpdateProfileTO updateUserTO = new UpdateProfileTO();
		updateUserTO.setId(1L);
		updateUserTO.setPassword("aaa");

		UserProfileTO userTO = new UserProfileTO();
		userTO.setId(1L);
		userTO.setPassword("aaa");
		
		when(userDao.findById(1L)).thenReturn(userTO);

		//when
		userValidation.validate(updateUserTO);
		
		//then
		fail("This test should throw UserValidationException.");		
	}
	
	@Test
	public void shouldExistentPlayerValidation() throws UserValidationException{
		//given
		UserValidationService userValidation = new UserValidationServiceImpl(userDao);
		UpdateProfileTO updateUserTO = new UpdateProfileTO();
		updateUserTO.setId(1L);
		updateUserTO.setPassword("aa12345");
		
		UserProfileTO userTO = new UserProfileTO();
		userTO.setId(1L);
		userTO.setPassword("aa12345");
		
		when(userDao.findById(1L)).thenReturn(userTO);

		//when
		userValidation.validate(updateUserTO);
		
		//then
		verify(userDao).findById(userTO.getId());
	}
	
	
}
