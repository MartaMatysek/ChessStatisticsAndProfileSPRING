package com.capgemini.chess.service.access.dao;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

public interface UserDao {

	UserProfileTO findById(Long id);
	
	List<UserStatisticsTO> readRanking();
	
}
