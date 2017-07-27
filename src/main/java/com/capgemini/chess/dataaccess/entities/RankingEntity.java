package com.capgemini.chess.dataaccess.entities;

import java.util.List;


public class RankingEntity{

	private int userRankingPosition;
	private int userLevel;
	private List<UserStatisticsEntity> listOfAllUsersStatistics;
	
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
	public List<UserStatisticsEntity> getListOfAllUsersStatistics() {
		return listOfAllUsersStatistics;
	}
	public void setListOfAllUsersStatistics(List<UserStatisticsEntity> listOfAllUsersStatistics) {
		this.listOfAllUsersStatistics = listOfAllUsersStatistics;
	}	
}
