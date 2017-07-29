package com.capgemini.chess.service;

import com.capgemini.chess.service.to.UserStatisticsTO;

public interface StatisticsSaveService {

	void save(UserStatisticsTO userStatisticsTO);
	
}
