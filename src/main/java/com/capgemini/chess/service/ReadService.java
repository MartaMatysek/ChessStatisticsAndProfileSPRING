package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.UserStatisticsTO;

public interface ReadService {

	public List<UserStatisticsTO> readRanking();
}