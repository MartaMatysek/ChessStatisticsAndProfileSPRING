package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.service.ReadService;
import com.capgemini.chess.service.access.dao.UserDao;
import com.capgemini.chess.service.to.UserStatisticsTO;

@Service
public class ReadServiceImpl implements ReadService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<UserStatisticsTO> readRanking(){
		List<UserStatisticsTO> listOfUserStatistics = userDao.readRanking();
		return listOfUserStatistics;
	}
	
}
