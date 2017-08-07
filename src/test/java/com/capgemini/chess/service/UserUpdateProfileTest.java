package com.capgemini.chess.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.impl.UserUpdateProfileServiceImpl;
import com.capgemini.chess.service.to.UpdateProfileTO;
import com.capgemini.chess.service.to.UserProfileTO;

@RunWith(MockitoJUnitRunner.class)
public class UserUpdateProfileTest {

	@Mock
	UserDao userDao;
	
	@Mock
	UserValidationService userValidation;
	
	
	@Test
	public void shouldUpdateProfile() throws UserValidationException{
		//given
		UserUpdateProfileService userUpdateProfile = new UserUpdateProfileServiceImpl(userValidation, userDao);
		UpdateProfileTO userBeforeUpdate = new UpdateProfileTO();
		UserProfileTO userAfterUpdate = new UserProfileTO();
		
		when(userDao.updateProfile(userBeforeUpdate)).thenReturn(userAfterUpdate);
		
		//when
		userUpdateProfile.update(userBeforeUpdate);
		
		//then
		verify(userDao).updateProfile(userBeforeUpdate);
	}
	
	@Test(expected= UserValidationException.class)
	public void shouldThrowExcetionInsteadUpdateProfile() throws UserValidationException{
		//given
		UserUpdateProfileService userUpdateProfile = new UserUpdateProfileServiceImpl(userValidation, userDao);
		UpdateProfileTO userBeforeUpdate = new UpdateProfileTO();
		UserProfileTO userAfterUpdate = new UserProfileTO();
		
		when(userDao.updateProfile(userBeforeUpdate)).thenReturn(userAfterUpdate);
		doThrow(new UserValidationException("Wrong ID!")).when(userValidation).validate(userBeforeUpdate);
		
		//when
		userUpdateProfile.update(userBeforeUpdate);
		
		//then
		fail("This test should throw UserValidationException!");
	}

}
