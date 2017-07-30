package com.capgemini.chess.service;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.service.impl.RankingCreationServiceImpl;
import com.capgemini.chess.service.to.RankingTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@RunWith(MockitoJUnitRunner.class)
public class RankingCreationServiceTest {
	
	@Mock
	ReadService readService;
	
	@Test
	public void shouldCreationRanking(){
		//given
		RankingCreationService ranking = new RankingCreationServiceImpl(readService);
		when(readService.readRanking()).thenReturn(usersForTest());
		
		//when
		RankingTO result = ranking.create(1L);
		
		//then
		assertEquals(2, result.getUserLevel());
		assertEquals(2, result.getUserRankingPosition());
		assertEquals(3, result.getListOfUsersStatistics().size());
	}

	private static List<UserStatisticsTO> usersForTest(){
		List<UserStatisticsTO> users = new ArrayList<>();
		UserStatisticsTO firstUser = new UserStatisticsTO();
		firstUser.setId(1L);
		firstUser.setPoints(20);
		firstUser.setLevel(2);
		firstUser.setNumberOfWonMatches(4);
		firstUser.setNumberOfLostMatches(1);
		firstUser.setNumberOfDrawMatches(2);
		
		UserStatisticsTO secondUser = new UserStatisticsTO();
		secondUser.setId(2L);
		secondUser.setPoints(5);
		secondUser.setLevel(1);
		secondUser.setNumberOfWonMatches(3);
		secondUser.setNumberOfLostMatches(5);
		secondUser.setNumberOfDrawMatches(7);
		
		UserStatisticsTO thirsUser = new UserStatisticsTO();
		thirsUser.setId(3L);
		thirsUser.setPoints(55);
		thirsUser.setLevel(3);
		thirsUser.setNumberOfWonMatches(20);
		thirsUser.setNumberOfLostMatches(9);
		thirsUser.setNumberOfDrawMatches(1);
		
		users.add(firstUser);
		users.add(secondUser);
		users.add(thirsUser);
				
		return users;
	}
}
