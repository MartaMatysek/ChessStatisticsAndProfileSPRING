package com.capgemini.chess.service;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.dataaccess.enums.MatchResult;
import com.capgemini.chess.service.impl.StatisticsUpdateServiceImpl;
import com.capgemini.chess.service.to.MatchTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsUpdateServiceTest {

	@Mock
	ReadService readMatch;
	
	@Mock
	StatisticsSaveService saveStatistics;
	
	@Test
	public void shouldStatisticsUpdateWhenFirstPlayerWon(){
		//given
		StatisticsUpdateService updateStatistics = new StatisticsUpdateServiceImpl(readMatch, saveStatistics);
		MatchTO match = new MatchTO();
		match.setMatchId(1L);
		match.setFirstPlayerId(1L);
		match.setSecondPlayerId(2l);
		match.setMatchResult(MatchResult.WON);
		
		UserStatisticsTO firstPlayer = getFirstPlayerStatistics();
		UserStatisticsTO secondPlayer = getSecondPlayerStatistics();
		
		when(readMatch.readUserStatistics(match.getFirstPlayerId())).thenReturn(firstPlayer);
		when(readMatch.readUserStatistics(match.getSecondPlayerId())).thenReturn(secondPlayer);
		doNothing().when(saveStatistics).save(secondPlayer);
		doNothing().when(saveStatistics).save(secondPlayer);		
		
		//when
		updateStatistics.update(match);
		
		//then
		assertEquals(160, firstPlayer.getPoints());
		assertEquals(33, firstPlayer.getNumberOfWonMatches());
		assertEquals(50, secondPlayer.getPoints());
		assertEquals(3, secondPlayer.getNumberOfLostMatches());
		
	}
	
	@Test
	public void shouldStatisticsUpdateWhenFirstPlayerLost(){
		//given
		StatisticsUpdateService updateStatistics = new StatisticsUpdateServiceImpl(readMatch, saveStatistics);
		MatchTO match = new MatchTO();
		match.setMatchId(1L);
		match.setFirstPlayerId(1L);
		match.setSecondPlayerId(2l);
		match.setMatchResult(MatchResult.LOST);
		
		UserStatisticsTO firstPlayer = getFirstPlayerStatistics();
		UserStatisticsTO secondPlayer = getSecondPlayerStatistics();
		
		when(readMatch.readUserStatistics(match.getFirstPlayerId())).thenReturn(firstPlayer);
		when(readMatch.readUserStatistics(match.getSecondPlayerId())).thenReturn(secondPlayer);
		doNothing().when(saveStatistics).save(secondPlayer);
		doNothing().when(saveStatistics).save(secondPlayer);		
		
		//when
		updateStatistics.update(match);
		
		//then
		assertEquals(150, firstPlayer.getPoints());
		assertEquals(2, firstPlayer.getNumberOfLostMatches());
		assertEquals(60, secondPlayer.getPoints());
		assertEquals(14, secondPlayer.getNumberOfWonMatches());
		
	}
	
	@Test
	public void shouldStatisticsUpdateWhenDraw(){
		//given
		StatisticsUpdateService updateStatistics = new StatisticsUpdateServiceImpl(readMatch, saveStatistics);
		MatchTO match = new MatchTO();
		match.setMatchId(1L);
		match.setFirstPlayerId(1L);
		match.setSecondPlayerId(2l);
		match.setMatchResult(MatchResult.DRAW);
		
		UserStatisticsTO firstPlayer = getFirstPlayerStatistics();
		UserStatisticsTO secondPlayer = getSecondPlayerStatistics();
		
		when(readMatch.readUserStatistics(match.getFirstPlayerId())).thenReturn(firstPlayer);
		when(readMatch.readUserStatistics(match.getSecondPlayerId())).thenReturn(secondPlayer);
		doNothing().when(saveStatistics).save(secondPlayer);
		doNothing().when(saveStatistics).save(secondPlayer);		
		
		//when
		updateStatistics.update(match);
		
		//then
		assertEquals(155, firstPlayer.getPoints());
		assertEquals(4, firstPlayer.getNumberOfDrawMatches());
		assertEquals(55, secondPlayer.getPoints());		
		
	}
	
	private static UserStatisticsTO getFirstPlayerStatistics(){
		UserStatisticsTO userStatistics = new UserStatisticsTO();
		userStatistics.setId(1L);
		userStatistics.setLevel(4);
		userStatistics.setPoints(155);
		userStatistics.setNumberOfWonMatches(32);
		userStatistics.setNumberOfLostMatches(1);
		userStatistics.setNumberOfDrawMatches(3);
		return userStatistics;
	}
	
	private static UserStatisticsTO getSecondPlayerStatistics(){
		UserStatisticsTO userStatistics = new UserStatisticsTO();
		userStatistics.setId(2L);
		userStatistics.setLevel(3);
		userStatistics.setPoints(55);
		userStatistics.setNumberOfWonMatches(13);
		userStatistics.setNumberOfLostMatches(2);
		userStatistics.setNumberOfDrawMatches(6);
		return userStatistics;
	}
}
