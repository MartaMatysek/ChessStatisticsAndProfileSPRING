package com.capgemini.chess.service.to;

import java.util.List;

public class RankingTO {
	
	private int userRankingPosition;
	private int userLevel;
	private List<UserStatisticsTO> listOfUserStatistics;
	
	public int getUserRankingPosition() {
		return userRankingPosition;
	}
	public void setUserRankingPosition(int userRankingPosition) {
		this.userRankingPosition = userRankingPosition;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public List<UserStatisticsTO> getListOfUserStatistics() {
		return listOfUserStatistics;
	}
	public void setListOfUserStatistics(List<UserStatisticsTO> listOfAllUsersStatistics) {
		this.listOfUserStatistics = listOfAllUsersStatistics;
	}
}
