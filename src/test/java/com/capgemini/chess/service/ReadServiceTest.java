package com.capgemini.chess.service;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.impl.ReadServiceImpl;
import com.capgemini.chess.service.to.UserStatisticsTO;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class ReadServiceTest {

	@Mock
	UserDao userDao;
	
	@Test
	public void shouldReadRanking(){
		//given
		ReadService readService = new ReadServiceImpl(userDao);
		UserStatisticsTO firstUserStatistcs = new UserStatisticsTO();
		
		UserStatisticsTO secondUserStatistics = new UserStatisticsTO();
		
		List<UserStatisticsTO> users = new ArrayList<>();
		users.add(firstUserStatistcs);
		users.add(secondUserStatistics);
		
		when(userDao.readRanking()).thenReturn(users);
		
		//when
		List<UserStatisticsTO> listOfUsers = readService.readRanking();

		//then
		assertEquals(listOfUsers, users);	
		assertEquals(listOfUsers.size(), 2);	
	}
	
	@Test
	public void shouldReadUserStatistics(){
		//given
		ReadService readService = new ReadServiceImpl(userDao);
		UserStatisticsTO user = new UserStatisticsTO();
		user.setId(1L);
		user.setLevel(1);
		user.setPoints(5);
		user.setNumberOfWonMatches(5);
		user.setNumberOfLostMatches(4);
		user.setNumberOfDrawMatches(3);
		long id = user.getId();
		
		when(userDao.readUserStatistics(id)).thenReturn(user);
		
		//when
		UserStatisticsTO userStatistics = readService.readUserStatistics(id);
		
		//then
		assertEquals(userStatistics, user);
		assertEquals(5, userStatistics.getPoints());
	}
	
}
