package com.capgemini.chess.service;

import com.capgemini.chess.service.to.UserStatisticsTO;

public interface StatisticsUpdateSaveService {

	void save(UserStatisticsTO userStatisticsTO);
	
}
