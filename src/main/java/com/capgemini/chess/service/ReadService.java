package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.UserProfileTO;
import com.capgemini.chess.service.to.UserStatisticsTO;

public interface ReadService {

	List<UserStatisticsTO> readRanking();

	UserStatisticsTO readUserStatistics(Long id);

	List<UserProfileTO> readUsersByLevelOrWonMatches(int level, int wonMatches);

	List<UserProfileTO> readUsersByName(String name);
}
