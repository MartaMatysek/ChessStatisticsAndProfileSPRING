package com.capgemini.chess.service.access.dao;

import java.util.List;

import com.capgemini.chess.dataaccess.entities.UserProfileEntity;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

public interface UserDao {
	
	void addAll(List<UserProfileEntity> users);

	UserProfileTO findById(Long id);
			
	UserProfileTO updateProfile(UserProfileTO userProfileTO);
	
	List<UserStatisticsTO> readRanking();
	
	UserStatisticsTO readUserStatistics(Long id);
	
	void saveUserStatistics(UserStatisticsTO userStatisticsTO);
	
	List<UserProfileTO> readUsersByLevel(int level);
	
	List<UserProfileTO> readUsersByWonMatches(int wonMatches);
	
	List<UserProfileTO> readUsersByName(String name);
}
