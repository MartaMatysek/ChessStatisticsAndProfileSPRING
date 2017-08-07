package com.capgemini.chess.service;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.dataaccess.dao.impl.MapUserDaoImpl;
import com.capgemini.chess.dataaccess.entities.UserProfileEntity;
import com.capgemini.chess.dataaccess.entities.UserStatisticsEntity;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UpdateProfileTO;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

public class UserDAOTest {

	@Test
	public void shouldfindUserById(){
		//given
		UserDao userDao = new MapUserDaoImpl();
		userDao.addAll(usersListForTest());
		UserProfileTO user = new UserProfileTO();
		
		//when
		user = userDao.findById(1L);
		
		//then
		assertEquals("janeczek", user.getLogin());
	}
	
	@Test(expected= AssertionError.class)
	public void shoudThrowExceptionDuringFindUserById(){
		//given
		UserDao userDao = new MapUserDaoImpl();
		userDao.addAll(usersListForTest());
		
		//when
		userDao.findById(5L);
		
		//then
		fail("This test should throw exception!");
	}
	
	@Test
	public void shoudUpdateProfile(){
		//given
		UserDao userDao = new MapUserDaoImpl();
		userDao.addAll(usersListForTest());
		UpdateProfileTO user = new UpdateProfileTO();
		user.setId(1L);
		user.setLogin("janeczek");
		user.setPassword("haslo12345");
		user.setEmail("zmianaMaila@wp.pl");
		
		//when
		UserProfileTO changeUser = userDao.updateProfile(user);
		
		//then
		assertEquals("zmianaMaila@wp.pl", changeUser.getEmail());
		assertEquals(null, changeUser.getName());
	}
	
	@Test
	public void shouldReadRanking(){
		//given
		UserDao userDao = new MapUserDaoImpl();
		userDao.addAll(usersListForTest());
		
		//when
		List<UserStatisticsTO> usersRanking = userDao.readRanking();
		
		//then
		assertEquals(6, usersRanking.size());
		assertEquals(20,usersRanking.get(0).getPoints());
	}
	
	@Test
	public void shouldReadUserStatistics(){
		//given
		UserDao userDao = new MapUserDaoImpl();
		userDao.addAll(usersListForTest());
		
		//when
		UserStatisticsTO userStatistics = userDao.readUserStatistics(2L);
		
		//then
		assertEquals(5, userStatistics.getPoints());
		assertEquals(7, userStatistics.getNumberOfDrawMatches());
	}
	
	@Test(expected= NullPointerException.class)
	public void shouldThrowExceptionDuringReadUserStatistics() {
		// given
		UserDao userDao = new MapUserDaoImpl();
		userDao.addAll(usersListForTest());

		// when
		userDao.readUserStatistics(5L);
		
		//then 
		fail("This test should throw exception");
	}
	
	
	@Test
	public void shouldSaveUserStatistics(){
		// given
		UserDao userDao = new MapUserDaoImpl();
		userDao.addAll(usersListForTest());
		UserStatisticsTO beforeUserStat = userDao.readUserStatistics(1L);
		
		UserStatisticsTO afterUserStat = new UserStatisticsTO();
		afterUserStat.setId(1L);
		afterUserStat.setPoints(25);
		afterUserStat.setLevel(2);
		afterUserStat.setNumberOfWonMatches(5);
		afterUserStat.setNumberOfLostMatches(1);
		afterUserStat.setNumberOfDrawMatches(2);
		
		//when
		userDao.saveUserStatistics(afterUserStat);
		
		//then
		assertTrue(beforeUserStat.getNumberOfLostMatches() == afterUserStat.getNumberOfLostMatches());
		assertFalse(beforeUserStat.getPoints() == afterUserStat.getPoints());			
	}
	
	
	private static List<UserProfileEntity> usersListForTest(){
		List<UserProfileEntity> users = new ArrayList<>();
		
		UserStatisticsEntity firstUserStat = new UserStatisticsEntity();
		firstUserStat.setId(1L);
		firstUserStat.setPoints(20);
		firstUserStat.setLevel(2);
		firstUserStat.setNumberOfWonMatches(4);
		firstUserStat.setNumberOfLostMatches(1);
		firstUserStat.setNumberOfDrawMatches(2);
		
		UserProfileEntity firstUser = new UserProfileEntity();
		firstUser.setId(1L);
		firstUser.setLogin("janeczek");
		firstUser.setPassword("jan123");
		firstUser.setName("Jan");
		firstUser.setSurname("Kowalski");
		firstUser.setAboutMe("I am who I am.");
		firstUser.setLifeMotto("Carpie Diem");
		firstUser.setUserStatistics(firstUserStat);
		
		UserStatisticsEntity secondUserStat = new UserStatisticsEntity();
		secondUserStat.setId(2L);
		secondUserStat.setPoints(5);
		secondUserStat.setLevel(1);
		secondUserStat.setNumberOfWonMatches(3);
		secondUserStat.setNumberOfLostMatches(5);
		secondUserStat.setNumberOfDrawMatches(7);
		
		UserProfileEntity secondUser = new UserProfileEntity();
		secondUser.setId(2L);
		secondUser.setLogin("marecki");
		secondUser.setPassword("ma10nie11");
		secondUser.setName("Marek");
		secondUser.setSurname("Abacki");
		secondUser.setAboutMe("I like dog.");
		secondUser.setLifeMotto("Life if easy.");
		secondUser.setUserStatistics(secondUserStat);
		
		UserStatisticsEntity thirdUserStat = new UserStatisticsEntity();
		thirdUserStat.setId(3L);
		thirdUserStat.setPoints(55);
		thirdUserStat.setLevel(3);
		thirdUserStat.setNumberOfWonMatches(20);
		thirdUserStat.setNumberOfLostMatches(9);
		thirdUserStat.setNumberOfDrawMatches(1);
	
		UserProfileEntity thirdUser = new UserProfileEntity();
		thirdUser.setId(3L);
		thirdUser.setLogin("mis");
		thirdUser.setPassword("haslo123");
		thirdUser.setName("Michał");
		thirdUser.setSurname("Michals");
		thirdUser.setAboutMe("Smile.");
		thirdUser.setLifeMotto("Chess");
		thirdUser.setUserStatistics(thirdUserStat);
		
		users.add(firstUser);
		users.add(secondUser);
		users.add(thirdUser);
		
		return users;
	}

}
