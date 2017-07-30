package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.StatisticsSaveService;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class StatisticsSaveServiceImpl implements StatisticsSaveService {

	private UserDao userDao;
	
	@Autowired
	public StatisticsSaveServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(UserStatisticsTO userStatisticsTO) {
		userDao.saveUserStatistics(userStatisticsTO);		
	}
	
	

}
