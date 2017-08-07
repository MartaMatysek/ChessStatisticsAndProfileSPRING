package com.capgemini.chess.service;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.exception.MatchValidationException;
import com.capgemini.chess.exception.UserValidationException;
import com.capgemini.chess.service.impl.MatchRegisterServiceImpl;
import com.capgemini.chess.service.to.MatchTO;

@RunWith(MockitoJUnitRunner.class)
public class MatchRegisterServiceTest {
	
	@Mock
	StatisticsUpdateService statisticsUpdate;
	
	@Mock
	MatchSaveService matchSave;
	
	@Mock
	UserValidationService userValidation;
	
	@Mock
	MatchValidationService matchValidation;
	
	@Test
	public void shouldRegisterMatch() throws UserValidationException, MatchValidationException{
		//given
		MatchRegisterService registerMatch = new MatchRegisterServiceImpl(statisticsUpdate, matchSave, userValidation, matchValidation);
		MatchTO match = new MatchTO();
		
		when(matchSave.save(match)).thenReturn(match);
		
		//when
		registerMatch.register(match);
		
		//then
		verify(statisticsUpdate, times(1)).update(match);
		verify(matchSave, times(1)).save(match);
	}
}
