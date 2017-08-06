package com.capgemini.chess.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.entities.UserProfileEntity;
import com.capgemini.chess.dataaccess.entities.UserStatisticsEntity;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.mapper.UserProfileMapper;
import com.capgemini.chess.service.mapper.UserStatisticsMapper;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Repository
public class MapUserDaoImpl implements UserDao{
	
	private List<UserProfileEntity> userProfiles = new ArrayList<>();
	
	{
		UserStatisticsEntity firstStatUser = new UserStatisticsEntity();
		firstStatUser.setId(1L);
		firstStatUser.setPoints(20);
		firstStatUser.setLevel(2);
		firstStatUser.setNumberOfWonMatches(4);
		firstStatUser.setNumberOfLostMatches(1);
		firstStatUser.setNumberOfDrawMatches(2);
		
		UserProfileEntity firstUser = new UserProfileEntity();
		firstUser.setId(1L);
		firstUser.setLogin("janeczek");
		firstUser.setPassword("jan123");
		firstUser.setName("Jan");
		firstUser.setSurname("Kowalski");
		firstUser.setEmail("j@wp.pl");
		firstUser.setAboutMe("I am who I am.");
		firstUser.setLifeMotto("Carpie Diem");
		firstUser.setUserStatistics(firstStatUser);
		
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
		
		userProfiles.add(firstUser);
		userProfiles.add(secondUser);
		userProfiles.add(thirdUser);
	}

	@Override
	public void addAll(List<UserProfileEntity> users) {
		userProfiles.addAll(users);
	}
	
	@Override
	public UserProfileTO findById(Long id) {
		UserProfileEntity userProfileEntity = userProfiles.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		return UserProfileMapper.map(userProfileEntity);
	}
	
	@Override
	public UserProfileTO updateProfile(UserProfileTO userProfileTO) {
		UserProfileEntity userProfileEntity = userProfiles.stream().filter(u -> u.getId().equals(userProfileTO.getId())).findFirst().orElse(null);
		userProfileEntity = UserProfileMapper.update(userProfileEntity, userProfileTO);
		return UserProfileMapper.map(userProfileEntity);
	}


	@Override
	public List<UserStatisticsTO> readRanking(){
		List<UserStatisticsEntity> usersStatisticsEntities = new ArrayList<>();
		for(int i = 0; i < userProfiles.size(); i++){
			usersStatisticsEntities.add(userProfiles.get(i).getUserStatistics());
		}
		
		return UserStatisticsMapper.map2TOs(usersStatisticsEntities);
	}
	
	@Override
	public UserStatisticsTO readUserStatistics(Long id){
		UserProfileEntity userProfileEntity = userProfiles.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		UserStatisticsEntity userStatisticsEntity = userProfileEntity.getUserStatistics();
		return UserStatisticsMapper.map(userStatisticsEntity);	
	}
	
	@Override
	public void saveUserStatistics(UserStatisticsTO userStatisticsTO){
		UserStatisticsEntity userStatisticsEntity = UserStatisticsMapper.map(userStatisticsTO);
		Long id = userStatisticsEntity.getId();
		UserProfileEntity userProfileEntity = userProfiles.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		userProfileEntity.setUserStatistics(userStatisticsEntity);
	}
	
	@Override
	public List<UserProfileTO> readUsersByLevel(int level){
		List<UserProfileEntity> usersWithGivenLevel = new ArrayList<>();
		for(UserProfileEntity user : userProfiles){
			if(user.getUserStatistics().getLevel() == level){
				usersWithGivenLevel.add(user);
			}
		}
		
		return UserProfileMapper.map2TOs(usersWithGivenLevel);		
	}
	
	@Override
	public List<UserProfileTO> readUsersByWonMatches(int wonMatches){
		List<UserProfileEntity> usersWithGivenWonMatches = new ArrayList<>();
		for(UserProfileEntity user : userProfiles){
			if(user.getUserStatistics().getNumberOfWonMatches() == wonMatches){
				usersWithGivenWonMatches.add(user);
			}
		}
		
		return UserProfileMapper.map2TOs(usersWithGivenWonMatches);		
	}
	
	@Override
	public List<UserProfileTO> readUsersByName(String name){
		List<UserProfileEntity> usersWithGivenName = new ArrayList<>();
		for(UserProfileEntity user : userProfiles){
			if(user.getName().equals(name)){
				usersWithGivenName.add(user);
			}
		}
		
		return UserProfileMapper.map2TOs(usersWithGivenName);		
	}
}
