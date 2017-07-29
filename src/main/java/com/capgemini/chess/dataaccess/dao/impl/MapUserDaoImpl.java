package com.capgemini.chess.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private final Map<Long, UserProfileEntity> userProfiles = new HashMap<>();

	@Override
	public UserProfileTO findById(Long id) {
		UserProfileEntity userProfileEntity = userProfiles.values().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		return UserProfileMapper.map(userProfileEntity);
	}
	
	@Override
	public UserProfileTO updateProfile(UserProfileTO userProfileTO) {
		UserProfileEntity userProfileEntity = userProfiles.values().stream().filter(u -> u.getId().equals(userProfileTO.getId())).findFirst().orElse(null);
		userProfileEntity = UserProfileMapper.update(userProfileEntity, userProfileTO);
		return UserProfileMapper.map(userProfileEntity);
	}

	@Override
	public List<UserStatisticsTO> readRanking(){
		List<UserStatisticsEntity> usersStatisticsEntities = new ArrayList<>();
		for(int i = 0; i < userProfiles.size(); i++){
			usersStatisticsEntities.add(userProfiles.get(i).getUserStatistics());
		}
		
		sort(usersStatisticsEntities);
		
		return UserStatisticsMapper.map2TOs(usersStatisticsEntities);
	}
	
	private List<UserStatisticsEntity> sort(List<UserStatisticsEntity> listOfUsersStatistics){
		Collections.sort(listOfUsersStatistics, Comparator.comparingInt(UserStatisticsEntity::getPoints).reversed());
		return listOfUsersStatistics;
	}
	
	@Override
	public UserStatisticsTO readUserStatistics(Long id){
		UserProfileEntity userProfileEntity = userProfiles.values().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		UserStatisticsEntity userStatisticsEntity = userProfileEntity.getUserStatistics();
		return UserStatisticsMapper.map(userStatisticsEntity);	
	}
	
	@Override
	public void saveUserStatistics(UserStatisticsTO userStatisticsTO){
		UserStatisticsEntity userStatisticsEntity = UserStatisticsMapper.map(userStatisticsTO);
		Long id = userStatisticsEntity.getId();
		UserProfileEntity userProfileEntity = userProfiles.values().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		userProfileEntity.setUserStatistics(userStatisticsEntity);
	}
}
