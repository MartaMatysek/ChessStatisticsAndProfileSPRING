package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class ReadServiceImpl {

	@Autowired
	private UserDao statisticsDao;
	
	public List<UserStatisticsTO> readRanking(){
		List<UserStatisticsTO> listOfUserStatistics = statisticsDao.readRanking();
		return listOfUserStatistics;
	}
	
}
