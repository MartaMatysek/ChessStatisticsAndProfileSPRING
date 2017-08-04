package com.capgemini.chess.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class ReadServiceImpl implements ReadService {

	private UserDao userDao;
	
	@Autowired
	public ReadServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<UserStatisticsTO> readRanking(){
		List<UserStatisticsTO> listOfUserStatistics = userDao.readRanking();
		return listOfUserStatistics;
	}

	@Override
	public UserStatisticsTO readUserStatistics(Long id) {
		UserStatisticsTO userStatisticsTO = userDao.readUserStatistics(id);
		return userStatisticsTO;
	}
	
	public List<UserProfileTO> readUsersByLevelOrWonMatches(int level, int wonMatches){
		List<UserProfileTO> users = new ArrayList<>();
		users.addAll(readUsersByGivenLevel(level));
		users.addAll(readUsersByGivenWonMatches(wonMatches));
		return users;
	}
	
	private List<UserProfileTO> readUsersByGivenLevel(int level){
		return userDao.readUsersByLevel(level);
	}
	
	private List<UserProfileTO> readUsersByGivenWonMatches(int wonMatches){
		return userDao.readUsersByWonMatches(wonMatches);
	}
}
