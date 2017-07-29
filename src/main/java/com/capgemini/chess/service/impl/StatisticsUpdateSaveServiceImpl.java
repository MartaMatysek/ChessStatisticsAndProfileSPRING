package com.capgemini.chess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.StatisticsUpdateSaveService;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class StatisticsUpdateSaveServiceImpl implements StatisticsUpdateSaveService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void save(UserStatisticsTO userStatisticsTO) {
		userDao.saveUserStatistics(userStatisticsTO);		
	}
	
	

}
