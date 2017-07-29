package com.capgemini.chess.service;

import com.capgemini.chess.service.to.MatchTO;

public interface StatisticsCalculateService {

	void wonFirstPlayerUpdateStatistics(MatchTO matchTO);
	
	void lostFirstPlayserUpdateStatistics(MatchTO matchTO);
	
	void drawUpdateStatistics(MatchTO matchTO);
}
