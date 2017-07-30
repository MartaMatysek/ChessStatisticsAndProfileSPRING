package com.capgemini.chess.service;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.impl.StatisticsSaveServiceImpl;
import com.capgemini.chess.service.to.UserStatisticsTO;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsSaveServiceTest {

	@Mock
	UserDao userDao;
	
	@Test
	public void shouldSaveUserStatistics(){
		//given
		StatisticsSaveService saveStatistics = new StatisticsSaveServiceImpl(userDao);
		UserStatisticsTO userStatistics = new UserStatisticsTO();
		
		doNothing().when(userDao).saveUserStatistics(userStatistics);
		
		//when
		saveStatistics.save(userStatistics);
		
		//then
		verify(userDao, times(1)).saveUserStatistics(userStatistics);
		
	}
	
}
