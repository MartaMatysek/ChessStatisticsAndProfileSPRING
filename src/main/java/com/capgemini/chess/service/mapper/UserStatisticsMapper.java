package com.capgemini.chess.service.mapper;

import com.capgemini.chess.dataaccess.entities.UserStatisticsEntity;
import com.capgemini.chess.service.to.UserStatisticsTO;

public class UserStatisticsMapper {
	
	public static UserStatisticsTO map(UserStatisticsEntity userStatisticsEntity){
		if(userStatisticsEntity != null){
			UserStatisticsTO userStatisticsTO = new UserStatisticsTO();
			userStatisticsTO.setId(userStatisticsEntity.getId());
			userStatisticsTO.setLevel(userStatisticsEntity.getLevel());
			userStatisticsTO.setPoints(userStatisticsEntity.getPoints());
			userStatisticsTO.setNumberOfWonMatches(userStatisticsEntity.getNumberOfWonMatches());
			userStatisticsTO.setNumberOfLostMatches(userStatisticsEntity.getNumberOfLostMatches());
			userStatisticsTO.setNumberOfDrawMatches(userStatisticsEntity.getNumberOfDrawMatches());
			return userStatisticsTO;
		}
		
		return null;
	}
	
	public static UserStatisticsEntity map(UserStatisticsTO userStatisticsTO){
		if(userStatisticsTO != null){
			UserStatisticsEntity userStatisticsEntity = new UserStatisticsEntity();
			userStatisticsEntity.setId(userStatisticsTO.getId());
			userStatisticsEntity.setLevel(userStatisticsTO.getLevel());
			userStatisticsEntity.setPoints(userStatisticsTO.getPoints());
			userStatisticsEntity.setNumberOfWonMatches(userStatisticsTO.getNumberOfWonMatches());
			userStatisticsEntity.setNumberOfLostMatches(userStatisticsTO.getNumberOfLostMatches());
			userStatisticsEntity.setNumberOfDrawMatches(userStatisticsTO.getNumberOfDrawMatches());
			return userStatisticsEntity;
		}
		
		return null;
	}
}
