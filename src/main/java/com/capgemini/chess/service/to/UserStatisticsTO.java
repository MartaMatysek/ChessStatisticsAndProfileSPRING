package com.capgemini.chess.service.to;

public class UserStatisticsTO {

	private long id;
	private int points;
	private int level;
	private int numberOfWonMatches;
	private int numberOfLostMatches;
	private int numberOfDrawMatches;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNumberOfWonMatches() {
		return numberOfWonMatches;
	}

	public void setNumberOfWonMatches(int numberOfWonMatches) {
		this.numberOfWonMatches = numberOfWonMatches;
	}

	public int getNumberOfLostMatches() {
		return numberOfLostMatches;
	}

	public void setNumberOfLostMatches(int numberOfLostMatches) {
		this.numberOfLostMatches = numberOfLostMatches;
	}

	public int getNumberOfDrawMatches() {
		return numberOfDrawMatches;
	}

	public void setNumberOfDrawMatches(int numberOfDrawMatches) {
		this.numberOfDrawMatches = numberOfDrawMatches;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + level;
		result = prime * result + numberOfDrawMatches;
		result = prime * result + numberOfLostMatches;
		result = prime * result + numberOfWonMatches;
		result = prime * result + points;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserStatisticsTO other = (UserStatisticsTO) obj;
		if (id != other.id)
			return false;
		if (level != other.level)
			return false;
		if (numberOfDrawMatches != other.numberOfDrawMatches)
			return false;
		if (numberOfLostMatches != other.numberOfLostMatches)
			return false;
		if (numberOfWonMatches != other.numberOfWonMatches)
			return false;
		if (points != other.points)
			return false;
		return true;
	}

}
