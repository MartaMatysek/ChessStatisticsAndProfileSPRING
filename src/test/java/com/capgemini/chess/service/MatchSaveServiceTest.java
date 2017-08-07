package com.capgemini.chess.service;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.service.access.dao.MatchDao;
import com.capgemini.chess.service.impl.MatchSaveServiceImpl;
import com.capgemini.chess.service.to.MatchTO;

@RunWith(MockitoJUnitRunner.class)
public class MatchSaveServiceTest {
	
	@Mock
	MatchDao matchDao;
	
	@Test
	public void shouldSaveMatch(){
		//given
		MatchSaveService saveMatch = new MatchSaveServiceImpl(matchDao);
		MatchTO match = new MatchTO();
		
		when(matchDao.saveMatch(match)).thenReturn(match);
		
		//when
		saveMatch.save(match);
		
		//then
		verify(matchDao, times(1)).saveMatch(match);
	}

}
