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
}
