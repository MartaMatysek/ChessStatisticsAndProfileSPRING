package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.entities.UserProfileEntity;
import com.capgemini.chess.service.to.UserProfileTO;

public class UserProfileMapper {
	
	public static UserProfileTO map(UserProfileEntity userProfileEntity) {
		if (userProfileEntity != null) {
			UserProfileTO userProfileTO = new UserProfileTO();
			userProfileTO.setAboutMe(userProfileEntity.getAboutMe());
			userProfileTO.setEmail(userProfileEntity.getEmail());
			userProfileTO.setId(userProfileEntity.getId());
			userProfileTO.setLifeMotto(userProfileEntity.getLifeMotto());
			userProfileTO.setLogin(userProfileEntity.getLogin());
			userProfileTO.setName(userProfileEntity.getName());
			userProfileTO.setPassword(userProfileEntity.getPassword());
			userProfileTO.setSurname(userProfileEntity.getSurname());
			userProfileTO.setUserStatistics(UserStatisticsMapper.map(userProfileEntity.getUserStatistics()));
			return userProfileTO;
		}
		return null;
	}

	public static UserProfileEntity map(UserProfileTO userProfileTO) {
		if (userProfileTO != null) {
			UserProfileEntity userProfileEntity = new UserProfileEntity();
			userProfileEntity.setAboutMe(userProfileTO.getAboutMe());
			userProfileEntity.setEmail(userProfileTO.getEmail());
			userProfileEntity.setId(userProfileTO.getId());
			userProfileEntity.setLifeMotto(userProfileTO.getLifeMotto());
			userProfileEntity.setLogin(userProfileTO.getLogin());
			userProfileEntity.setName(userProfileTO.getName());
			userProfileEntity.setPassword(userProfileTO.getPassword());
			userProfileEntity.setSurname(userProfileTO.getSurname());
			userProfileEntity.setUserStatistics(UserStatisticsMapper.map(userProfileTO.getUserStatistics()));
			return userProfileEntity;
		}
		return null;
	}
	
	public static UserProfileEntity update(UserProfileEntity userProfileEntity, UserProfileTO userProfileTO) {
		if (userProfileTO != null && userProfileEntity != null) {
			userProfileEntity.setAboutMe(userProfileTO.getAboutMe());
			userProfileEntity.setEmail(userProfileTO.getEmail());
			userProfileEntity.setId(userProfileTO.getId());
			userProfileEntity.setLifeMotto(userProfileTO.getLifeMotto());
			userProfileEntity.setName(userProfileTO.getName());
			userProfileEntity.setPassword(userProfileTO.getPassword());
			userProfileEntity.setSurname(userProfileTO.getSurname());
			userProfileEntity.setUserStatistics(UserStatisticsMapper.map(userProfileTO.getUserStatistics()));
		}
		return userProfileEntity;
	}
	
	public static List<UserProfileTO> map2TOs(List<UserProfileEntity> userProfileEntities) {
		return userProfileEntities.stream().map(UserProfileMapper::map).collect(Collectors.toList());
	}

	public static List<UserProfileEntity> map2Entities(List<UserProfileTO> userProfileTOs) {
		return userProfileTOs.stream().map(UserProfileMapper::map).collect(Collectors.toList());
	}
}
