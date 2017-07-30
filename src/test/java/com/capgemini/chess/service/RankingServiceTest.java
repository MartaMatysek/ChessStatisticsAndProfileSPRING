package com.capgemini.chess.service;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.impl.RankingServiceImpl;
import com.capgemini.chess.service.to.RankingTO;

@RunWith(MockitoJUnitRunner.class)
public class RankingServiceTest {

	@Mock
	UserValidationService userIdValidation;
	
	@Mock
	RankingCreationService rankingCreation;
	
	@Test
	public void shouldReturnNewRanking() throws UserValidationException{
		//given
		RankingService newCreationRanking = new RankingServiceImpl(userIdValidation, rankingCreation);
		RankingTO ranking = new RankingTO();
		
		doNothing().when(userIdValidation).validate(1L);
		when(rankingCreation.create(1L)).thenReturn(ranking);
		
		//when
		newCreationRanking.getRanking(1L);
		
		//then
		verify(userIdValidation, times(1)).validate(1L);
		verify(rankingCreation, times(1)).create(1L);
	}
	
	@Test(expected= UserValidationException.class) 
	public void shouldThrowExceptionDuringReturnNewRanking() throws UserValidationException{
		//given
		RankingService newCreationRanking = new RankingServiceImpl(userIdValidation, rankingCreation);
		RankingTO ranking = new RankingTO();
		doThrow(new UserValidationException("Wrong ID!")).when(userIdValidation).validate(1L);
		when(rankingCreation.create(1L)).thenReturn(ranking);
	
		//when
		newCreationRanking.getRanking(1L);
				
		//then
		fail("This method should UserValidationException.");
	}
	
}
