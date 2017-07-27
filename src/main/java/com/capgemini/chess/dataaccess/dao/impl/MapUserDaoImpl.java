package com.capgemini.chess.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.chess.dataaccess.entities.UserProfileEntity;
import com.capgemini.chess.dataaccess.entities.UserStatisticsEntity;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.mapper.UserProfileMapper;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

public class MapUserDaoImpl implements UserDao{
	
	private final Map<Long, UserProfileEntity> userProfiles = new HashMap<>();

	@Override
	public UserProfileTO findById(Long id) {
		UserProfileEntity userProfileEntity = userProfiles.values().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
		return UserProfileMapper.map(userProfileEntity);
	}

	@Override
	public List<UserStatisticsTO> readRanking() {
		List<UserStatisticsEntity> userStatisticsEntities = new ArrayList<>();
		for(int i = 0; i < userProfiles.size(); i++){
			userStatisticsEntities.add(userProfiles.get(i).getUserStatisticsEntity());
		}
		
		return null;
	}

}
